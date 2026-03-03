# BPE Tokenizer

An implementation of Byte Pair Encoding (BPE) tokenization.

## Overview

Byte Pair Encoding (BPE) is a subword tokenization algorithm used in NLP models
(e.g., GPT, RoBERTa). It iteratively merges the most frequent pair of adjacent
symbols in a corpus to build a vocabulary of subword units.

## Algorithm

1. **Train**: Given a corpus, start with a character-level vocabulary and
   repeatedly merge the most frequent adjacent byte/character pair until the
   target vocabulary size is reached.
2. **Encode**: Given a string, apply the learned merge rules in order to
   produce a sequence of token IDs.
3. **Decode**: Given a sequence of token IDs, reconstruct the original string.
