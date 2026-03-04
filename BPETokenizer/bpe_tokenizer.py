"""
BPE (Byte Pair Encoding) Tokenizer

Implementation of the BPE subword tokenization algorithm as described in:
  "Neural Machine Translation of Rare Words with Subword Units"
  Sennrich et al., 2016 — https://arxiv.org/abs/1508.07909

Algorithm overview:
  1. Train  — build a vocabulary by iteratively merging the most frequent
               adjacent symbol pair in a corpus until the target vocab size
               is reached.
  2. Encode — apply the learned merge rules to convert text into token IDs.
  3. Decode — convert token IDs back to the original text.
"""

from __future__ import annotations

import json
from collections import defaultdict
from typing import Dict, List, Tuple

EOW = "</w>"  # end-of-word marker appended to every word during training


class BPETokenizer:
    """
    Byte Pair Encoding tokenizer.

    Example
    -------
    >>> tok = BPETokenizer(vocab_size=50)
    >>> tok.train(["low lower lowest", "new newer newest"])
    >>> tok.tokenize("low")
    ['low</w>']
    >>> tok.encode("low")
    [...]
    >>> tok.decode(tok.encode("low"))
    'low'
    """

    def __init__(self, vocab_size: int = 256) -> None:
        """
        Args:
            vocab_size: Target vocabulary size (base characters + merged tokens).
                        Must be >= number of unique characters in the corpus.
        """
        self.vocab_size = vocab_size
        self.merges: List[Tuple[str, str]] = []   # ordered merge rules
        self.vocab: Dict[str, int] = {}           # token -> id
        self._id_to_token: Dict[int, str] = {}    # id -> token

    # ------------------------------------------------------------------
    # Training
    # ------------------------------------------------------------------

    def train(self, corpus: List[str]) -> "BPETokenizer":
        """
        Train the tokenizer on a corpus.

        Args:
            corpus: List of strings (sentences or raw words). Words are
                    separated by whitespace.

        Returns:
            self  (enables method chaining)
        """
        # Build a word-frequency table.
        # Each word is represented as a tuple of single characters + EOW.
        word_freqs: Dict[Tuple[str, ...], int] = defaultdict(int)
        for text in corpus:
            for word in text.strip().split():
                word_freqs[tuple(list(word) + [EOW])] += 1

        # Seed the vocabulary with every unique character seen in the corpus.
        base_chars: set[str] = set()
        for word in word_freqs:
            base_chars.update(word)

        self.vocab = {ch: idx for idx, ch in enumerate(sorted(base_chars))}
        self.merges = []

        # Iteratively merge the most frequent adjacent symbol pair.
        num_merges = self.vocab_size - len(self.vocab)
        for _ in range(max(0, num_merges)):
            pair_freqs = self._count_pairs(word_freqs)
            if not pair_freqs:
                break

            best_pair = max(pair_freqs, key=pair_freqs.__getitem__)
            word_freqs = self._apply_merge(best_pair, word_freqs)

            merged_token = best_pair[0] + best_pair[1]
            self.merges.append(best_pair)
            if merged_token not in self.vocab:
                self.vocab[merged_token] = len(self.vocab)

        self._id_to_token = {idx: tok for tok, idx in self.vocab.items()}
        return self

    @staticmethod
    def _count_pairs(
        word_freqs: Dict[Tuple[str, ...], int],
    ) -> Dict[Tuple[str, str], int]:
        """Return the frequency of every adjacent symbol pair across the corpus."""
        pair_freqs: Dict[Tuple[str, str], int] = defaultdict(int)
        for word, freq in word_freqs.items():
            for a, b in zip(word, word[1:]):
                pair_freqs[(a, b)] += freq
        return pair_freqs

    @staticmethod
    def _apply_merge(
        pair: Tuple[str, str],
        word_freqs: Dict[Tuple[str, ...], int],
    ) -> Dict[Tuple[str, ...], int]:
        """Replace every occurrence of *pair* in the vocabulary with the merged token."""
        a, b = pair
        merged = a + b
        new_word_freqs: Dict[Tuple[str, ...], int] = {}

        for word, freq in word_freqs.items():
            new_word: List[str] = []
            i = 0
            while i < len(word):
                if i + 1 < len(word) and word[i] == a and word[i + 1] == b:
                    new_word.append(merged)
                    i += 2
                else:
                    new_word.append(word[i])
                    i += 1
            new_word_freqs[tuple(new_word)] = freq

        return new_word_freqs

    # ------------------------------------------------------------------
    # Tokenization
    # ------------------------------------------------------------------

    def _tokenize_word(self, word: str) -> List[str]:
        """Apply all learned merge rules to a single word."""
        symbols: List[str] = list(word) + [EOW]

        for a, b in self.merges:
            merged = a + b
            i = 0
            new_symbols: List[str] = []
            while i < len(symbols):
                if i + 1 < len(symbols) and symbols[i] == a and symbols[i + 1] == b:
                    new_symbols.append(merged)
                    i += 2
                else:
                    new_symbols.append(symbols[i])
                    i += 1
            symbols = new_symbols

        return symbols

    def tokenize(self, text: str) -> List[str]:
        """
        Tokenize text into subword token strings.

        Args:
            text: Input text with space-separated words.

        Returns:
            List of token strings (e.g. ['low</w>', 'er</w>']).
        """
        tokens: List[str] = []
        for word in text.strip().split():
            tokens.extend(self._tokenize_word(word))
        return tokens

    def encode(self, text: str) -> List[int]:
        """
        Encode text into a list of token IDs.

        Args:
            text: Input text.

        Returns:
            List of integer token IDs. Tokens absent from the vocabulary
            receive ID -1.
        """
        return [self.vocab.get(tok, -1) for tok in self.tokenize(text)]

    def decode(self, token_ids: List[int]) -> str:
        """
        Decode a list of token IDs back to the original text.

        Args:
            token_ids: List of integer token IDs.

        Returns:
            Decoded string.
        """
        tokens = [self._id_to_token.get(tid, "") for tid in token_ids]
        return "".join(tokens).replace(EOW, " ").strip()

    # ------------------------------------------------------------------
    # Serialization
    # ------------------------------------------------------------------

    def save(self, path: str) -> None:
        """Persist the tokenizer to a JSON file.

        Args:
            path: Destination file path (e.g. "tokenizer.json").
        """
        data = {
            "vocab_size": self.vocab_size,
            "vocab": self.vocab,
            "merges": self.merges,
        }
        with open(path, "w", encoding="utf-8") as f:
            json.dump(data, f, indent=2, ensure_ascii=False)

    @classmethod
    def load(cls, path: str) -> "BPETokenizer":
        """Load a tokenizer from a JSON file created by :meth:`save`.

        Args:
            path: Path to the JSON file.

        Returns:
            A fully initialised :class:`BPETokenizer` instance.
        """
        with open(path, "r", encoding="utf-8") as f:
            data = json.load(f)

        tok = cls(vocab_size=data["vocab_size"])
        tok.vocab = data["vocab"]
        tok.merges = [tuple(pair) for pair in data["merges"]]
        tok._id_to_token = {idx: token for token, idx in tok.vocab.items()}
        return tok

    # ------------------------------------------------------------------
    # Helpers
    # ------------------------------------------------------------------

    def vocab_size_actual(self) -> int:
        """Return the number of tokens currently in the vocabulary."""
        return len(self.vocab)

    def __repr__(self) -> str:
        return (
            f"BPETokenizer(vocab_size={self.vocab_size}, "
            f"actual={self.vocab_size_actual()}, "
            f"merges={len(self.merges)})"
        )
