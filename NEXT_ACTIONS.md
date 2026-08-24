# Immediate Actions - High-Value Files

Based on AST analysis, here are the concrete next steps.

## Summary

- **Files Present:** 15/17 (88.2%)
- **Function parity:** 164/200 matched (target 230) — 82.0%
- **Class/type parity:** 10/15 matched (target 38) — 66.7%
- **Combined symbol parity:** 174/215 matched (target 268) — 80.9%
- **Average inline-code cosine:** 0.55 (function body across 15 matched files)
- **Average documentation cosine:** 0.60 (doc text across 15 matched files)
- **Cheat-zeroed Files:** 0
- **Critical Issues:** 8 files with <0.60 function similarity

## Priority 1: Fix Incomplete High-Dependency Files

No incomplete high-dependency files detected.

## Priority 2: Port Missing High-Value Files

Critical missing files (>10 dependencies):

No missing high-value files detected.

## Detailed Work Items

Every matched file is listed below with function and type symbol parity.

### 1. options

- **Target:** `textwrap.Options [PROVENANCE-FALLBACK]`
- **Similarity:** 0.75
- **Dependents:** 4
- **Priority Score:** 4001202.5
- **Functions:** 11/11 matched (target 13)
- **Missing functions:** _none_
- **Types:** 1/1 matched (target 2)
- **Missing types:** _none_
- **Tests:** 1/1 matched
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `options.rs` vs expected `options.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `tests:options.rs` vs expected `options.rs`
- **Proposed provenance header:** `// port-lint: source options.rs` (current: `// port-lint: source options.rs`)
- **Proposed provenance header:** `// port-lint: tests options.rs` (current: `// port-lint: tests options.rs`)
- **Lint issues:** 2

### 2. wrap

- **Target:** `textwrap.Wrap [PROVENANCE-FALLBACK]`
- **Similarity:** 0.70
- **Dependents:** 1
- **Priority Score:** 1024803.1
- **Functions:** 46/48 matched
- **Missing functions:** `borrowed_lines`, `break_words`
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 43/45 matched
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `wrap.rs` vs expected `wrap.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `tests:wrap.rs` vs expected `wrap.rs`
- **Proposed provenance header:** `// port-lint: source wrap.rs` (current: `// port-lint: source wrap.rs`)
- **Proposed provenance header:** `// port-lint: tests wrap.rs` (current: `// port-lint: tests wrap.rs`)
- **Lint issues:** 2

### 3. line_ending

- **Target:** `textwrap.LineEnding [PROVENANCE-FALLBACK]`
- **Similarity:** 0.39
- **Dependents:** 1
- **Priority Score:** 1010806.1
- **Functions:** 5/5 matched (target 7)
- **Missing functions:** _none_
- **Types:** 2/3 matched (target 4)
- **Missing types:** `Item`
- **Tests:** 3/3 matched
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `line_ending.rs` vs expected `line_ending.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `tests:line_ending.rs` vs expected `line_ending.rs`
- **Proposed provenance header:** `// port-lint: source line_ending.rs` (current: `// port-lint: source line_ending.rs`)
- **Proposed provenance header:** `// port-lint: tests line_ending.rs` (current: `// port-lint: tests line_ending.rs`)
- **Lint issues:** 2

### 4. termwidth

- **Target:** `textwrap.Termwidth [PROVENANCE-FALLBACK]`
- **Similarity:** 0.44
- **Dependents:** 1
- **Priority Score:** 1000205.6
- **Functions:** 2/2 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched
- **Missing types:** _none_
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `termwidth.rs` vs expected `termwidth.rs`
- **Proposed provenance header:** `// port-lint: source termwidth.rs` (current: `// port-lint: source termwidth.rs`)
- **Lint issues:** 1

### 5. wrap_algorithms.optimal_fit

- **Target:** `wrapalgorithms.OptimalFit [PROVENANCE-FALLBACK]`
- **Similarity:** 0.20
- **Dependents:** 0
- **Priority Score:** 91508.0
- **Functions:** 4/11 matched (target 5)
- **Missing functions:** `fmt`, `width`, `whitespace_width`, `penalty_width`, `wrap_fragments_with_infinite_widths`, `wrap_fragments_with_huge_widths`, `wrap_fragments_with_large_widths`
- **Types:** 2/4 matched
- **Missing types:** `OverflowError`, `Word`
- **Tests:** 0/6 matched
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `wrap_algorithms/optimal_fit.rs` vs expected `wrap_algorithms/optimal_fit.rs`
- **Proposed provenance header:** `// port-lint: source wrap_algorithms/optimal_fit.rs` (current: `// port-lint: source wrap_algorithms/optimal_fit.rs`)
- **Lint issues:** 1

### 6. word_separators

- **Target:** `textwrap.WordSeparators [PROVENANCE-FALLBACK]`
- **Similarity:** 0.24
- **Dependents:** 0
- **Priority Score:** 71107.6
- **Functions:** 3/10 matched (target 26)
- **Missing functions:** `eq`, `find_words_ascii_space`, `strip_ansi_escape_sequences`, `find_words_unicode_break_properties`, `to_words`, `find_words_colored_text`, `find_words_color_inside_word`
- **Types:** 1/1 matched (target 6)
- **Missing types:** _none_
- **Tests:** 1/4 matched
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `word_separators.rs` vs expected `word_separators.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `tests:word_separators.rs` vs expected `word_separators.rs`
- **Proposed provenance header:** `// port-lint: source word_separators.rs` (current: `// port-lint: source word_separators.rs`)
- **Proposed provenance header:** `// port-lint: tests word_separators.rs` (current: `// port-lint: tests word_separators.rs`)
- **Lint issues:** 2

### 7. wrap_algorithms

- **Target:** `textwrap.WrapAlgorithms [PROVENANCE-FALLBACK]`
- **Similarity:** 0.59
- **Dependents:** 0
- **Priority Score:** 61204.1
- **Functions:** 5/10 matched (target 21)
- **Missing functions:** `eq`, `default`, `width`, `whitespace_width`, `penalty_width`
- **Types:** 1/2 matched (target 8)
- **Missing types:** `Word`
- **Tests:** 1/4 matched
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `wrap_algorithms.rs` vs expected `wrap_algorithms.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `tests:wrap_algorithms.rs` vs expected `wrap_algorithms.rs`
- **Proposed provenance header:** `// port-lint: source wrap_algorithms.rs` (current: `// port-lint: source wrap_algorithms.rs`)
- **Proposed provenance header:** `// port-lint: tests wrap_algorithms.rs` (current: `// port-lint: tests wrap_algorithms.rs`)
- **Lint issues:** 2

### 8. fuzzing

- **Target:** `textwrap.Fuzzing [PROVENANCE-FALLBACK]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 30310.0
- **Functions:** 0/3 matched
- **Missing functions:** `fill_slow_path`, `wrap_single_line`, `wrap_single_line_slow_path`
- **Types:** 0/0 matched
- **Missing types:** _none_
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `fuzzing.rs` vs expected `fuzzing.rs`
- **Proposed provenance header:** `// port-lint: source fuzzing.rs` (current: `// port-lint: source fuzzing.rs`)
- **Lint issues:** 1

### 9. core

- **Target:** `textwrap.Core [PROVENANCE-FALLBACK]`
- **Similarity:** 0.54
- **Dependents:** 0
- **Priority Score:** 21904.6
- **Functions:** 15/16 matched (target 21)
- **Missing functions:** `deref`
- **Types:** 2/3 matched
- **Missing types:** `Target`
- **Tests:** 6/6 matched
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `core.rs` vs expected `core.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `tests:core.rs` vs expected `core.rs`
- **Proposed provenance header:** `// port-lint: source core.rs` (current: `// port-lint: source core.rs`)
- **Proposed provenance header:** `// port-lint: tests core.rs` (current: `// port-lint: tests core.rs`)
- **Lint issues:** 2

### 10. word_splitters

- **Target:** `textwrap.WordSplitters [PROVENANCE-FALLBACK]`
- **Similarity:** 0.68
- **Dependents:** 0
- **Priority Score:** 11003.2
- **Functions:** 8/9 matched (target 15)
- **Missing functions:** `eq`
- **Types:** 1/1 matched (target 6)
- **Missing types:** _none_
- **Tests:** 6/6 matched
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `word_splitters.rs` vs expected `word_splitters.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `tests:word_splitters.rs` vs expected `word_splitters.rs`
- **Proposed provenance header:** `// port-lint: source word_splitters.rs` (current: `// port-lint: source word_splitters.rs`)
- **Proposed provenance header:** `// port-lint: tests word_splitters.rs` (current: `// port-lint: tests word_splitters.rs`)
- **Lint issues:** 2

### 11. refill

- **Target:** `textwrap.Refill [PROVENANCE-FALLBACK]`
- **Similarity:** 0.73
- **Dependents:** 0
- **Priority Score:** 2202.7
- **Functions:** 22/22 matched (target 23)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 20/20 matched
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `refill.rs` vs expected `refill.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `tests:refill.rs` vs expected `refill.rs`
- **Proposed provenance header:** `// port-lint: source refill.rs` (current: `// port-lint: source refill.rs`)
- **Proposed provenance header:** `// port-lint: tests refill.rs` (current: `// port-lint: tests refill.rs`)
- **Lint issues:** 2

### 12. fill

- **Target:** `textwrap.Fill [PROVENANCE-FALLBACK]`
- **Similarity:** 0.80
- **Dependents:** 0
- **Priority Score:** 2102.0
- **Functions:** 21/21 matched (target 22)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 18/18 matched
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `fill.rs` vs expected `fill.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `tests:fill.rs` vs expected `fill.rs`
- **Proposed provenance header:** `// port-lint: source fill.rs` (current: `// port-lint: source fill.rs`)
- **Proposed provenance header:** `// port-lint: tests fill.rs` (current: `// port-lint: tests fill.rs`)
- **Lint issues:** 2

### 13. indentation

- **Target:** `textwrap.Indentation [PROVENANCE-FALLBACK]`
- **Similarity:** 0.50
- **Dependents:** 0
- **Priority Score:** 1505.0
- **Functions:** 15/15 matched (target 16)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 13/13 matched
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `indentation.rs` vs expected `indentation.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `tests:indentation.rs` vs expected `indentation.rs`
- **Proposed provenance header:** `// port-lint: source indentation.rs` (current: `// port-lint: source indentation.rs`)
- **Proposed provenance header:** `// port-lint: tests indentation.rs` (current: `// port-lint: tests indentation.rs`)
- **Lint issues:** 2

### 14. columns

- **Target:** `textwrap.Columns [PROVENANCE-FALLBACK]`
- **Similarity:** 0.61
- **Dependents:** 0
- **Priority Score:** 703.9
- **Functions:** 7/7 matched (target 8)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 6/6 matched
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `columns.rs` vs expected `columns.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `tests:columns.rs` vs expected `columns.rs`
- **Proposed provenance header:** `// port-lint: source columns.rs` (current: `// port-lint: source columns.rs`)
- **Proposed provenance header:** `// port-lint: tests columns.rs` (current: `// port-lint: tests columns.rs`)
- **Lint issues:** 2

### 15. lib

- **Target:** `textwrap.Lib [PROVENANCE-FALLBACK]`
- **Similarity:** 1.00
- **Dependents:** 0
- **Priority Score:** 0.0
- **Functions:** 0/0 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched
- **Missing types:** _none_
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `lib.rs` vs expected `lib.rs`
- **Proposed provenance header:** `// port-lint: source lib.rs` (current: `// port-lint: source lib.rs`)
- **Lint issues:** 1

## Success Criteria

For each file to be considered "complete":
- **Similarity ≥ 0.85** (Excellent threshold)
- All public APIs ported
- All tests ported
- Documentation ported
- port-lint header present

