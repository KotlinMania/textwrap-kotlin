# Immediate Actions - High-Value Files

Based on AST analysis, here are the concrete next steps.

## Summary

- **Files Present:** 15/15 (100.0%)
- **Function parity:** 183/190 matched (target 280) — 96.3%
- **Class/type parity:** 13/15 matched (target 42) — 86.7%
- **Combined symbol parity:** 196/205 matched (target 322) — 95.6%
- **Average inline-code cosine:** 0.66 (function body across 14 matched files)
- **Average documentation cosine:** 0.74 (doc text across 14 matched files)
- **Cheat-zeroed Files:** 1
- **Critical Issues:** 4 files with <0.60 function similarity

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
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `textwrap/src/options.rs` vs expected `options.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `tests:textwrap/src/options.rs` vs expected `options.rs`
- **Proposed provenance header:** `// port-lint: source options.rs` (current: `// port-lint: source textwrap/src/options.rs`)
- **Proposed provenance header:** `// port-lint: tests options.rs` (current: `// port-lint: tests textwrap/src/options.rs`)
- **Lint issues:** 2

### 2. wrap

- **Target:** `textwrap.Wrap [PROVENANCE-FALLBACK]`
- **Similarity:** 0.72
- **Dependents:** 1
- **Priority Score:** 1004802.8
- **Functions:** 48/48 matched (target 49)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 45/45 matched
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `textwrap/src/wrap.rs` vs expected `wrap.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `tests:textwrap/src/wrap.rs` vs expected `wrap.rs`
- **Proposed provenance header:** `// port-lint: source wrap.rs` (current: `// port-lint: source textwrap/src/wrap.rs`)
- **Proposed provenance header:** `// port-lint: tests wrap.rs` (current: `// port-lint: tests textwrap/src/wrap.rs`)
- **Lint issues:** 2

### 3. line_ending

- **Target:** `textwrap.LineEnding [PROVENANCE-FALLBACK]`
- **Similarity:** 0.39
- **Dependents:** 1
- **Priority Score:** 1000806.1
- **Functions:** 5/5 matched (target 7)
- **Missing functions:** _none_
- **Types:** 3/3 matched (target 5)
- **Missing types:** _none_
- **Tests:** 3/3 matched
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `textwrap/src/line_ending.rs` vs expected `line_ending.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `tests:textwrap/src/line_ending.rs` vs expected `line_ending.rs`
- **Proposed provenance header:** `// port-lint: source line_ending.rs` (current: `// port-lint: source textwrap/src/line_ending.rs`)
- **Proposed provenance header:** `// port-lint: tests line_ending.rs` (current: `// port-lint: tests textwrap/src/line_ending.rs`)
- **Lint issues:** 2

### 4. termwidth

- **Target:** `textwrap.Termwidth [PROVENANCE-FALLBACK]`
- **Similarity:** 0.44
- **Dependents:** 1
- **Priority Score:** 1000205.6
- **Functions:** 2/2 matched (target 3)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `textwrap/src/termwidth.rs` vs expected `termwidth.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `tests:textwrap/src/termwidth.rs` vs expected `termwidth.rs`
- **Proposed provenance header:** `// port-lint: source termwidth.rs` (current: `// port-lint: source textwrap/src/termwidth.rs`)
- **Proposed provenance header:** `// port-lint: tests termwidth.rs` (current: `// port-lint: tests textwrap/src/termwidth.rs`)
- **Lint issues:** 2

### 5. wrap_algorithms

- **Target:** `textwrap.WrapAlgorithms [PROVENANCE-FALLBACK]`
- **Similarity:** 0.80
- **Dependents:** 0
- **Priority Score:** 41202.0
- **Functions:** 7/10 matched (target 24)
- **Missing functions:** `width`, `whitespace_width`, `penalty_width`
- **Types:** 1/2 matched (target 7)
- **Missing types:** `WrapAlgorithm`
- **Tests:** 1/4 matched
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `textwrap/src/wrap_algorithms.rs` vs expected `wrap_algorithms.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `tests:textwrap/src/wrap_algorithms.rs` vs expected `wrap_algorithms.rs`
- **Proposed provenance header:** `// port-lint: source wrap_algorithms.rs` (current: `// port-lint: source textwrap/src/wrap_algorithms.rs`)
- **Proposed provenance header:** `// port-lint: tests wrap_algorithms.rs` (current: `// port-lint: tests textwrap/src/wrap_algorithms.rs`)
- **Lint issues:** 2

### 6. wrap_algorithms.optimal_fit

- **Target:** `wrapalgorithms.OptimalFit [PROVENANCE-FALLBACK]`
- **Similarity:** 0.61
- **Dependents:** 0
- **Priority Score:** 31503.9
- **Functions:** 8/11 matched (target 21)
- **Missing functions:** `width`, `whitespace_width`, `penalty_width`
- **Types:** 4/4 matched (target 8)
- **Missing types:** _none_
- **Tests:** 3/6 matched
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `textwrap/src/wrap_algorithms/optimal_fit.rs` vs expected `wrap_algorithms/optimal_fit.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `tests:textwrap/src/wrap_algorithms/optimal_fit.rs` vs expected `wrap_algorithms/optimal_fit.rs`
- **Proposed provenance header:** `// port-lint: source wrap_algorithms/optimal_fit.rs` (current: `// port-lint: source textwrap/src/wrap_algorithms/optimal_fit.rs`)
- **Proposed provenance header:** `// port-lint: tests wrap_algorithms/optimal_fit.rs` (current: `// port-lint: tests textwrap/src/wrap_algorithms/optimal_fit.rs`)
- **Lint issues:** 2

### 7. word_separators

- **Target:** `textwrap.WordSeparators [PROVENANCE-FALLBACK]`
- **Similarity:** 0.68
- **Dependents:** 0
- **Priority Score:** 21103.2
- **Functions:** 9/10 matched (target 48)
- **Missing functions:** `to_words`
- **Types:** 0/1 matched (target 4)
- **Missing types:** `WordSeparator`
- **Tests:** 3/4 matched
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `textwrap/src/word_separators.rs` vs expected `word_separators.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `tests:textwrap/src/word_separators.rs` vs expected `word_separators.rs`
- **Proposed provenance header:** `// port-lint: source word_separators.rs` (current: `// port-lint: source textwrap/src/word_separators.rs`)
- **Proposed provenance header:** `// port-lint: tests word_separators.rs` (current: `// port-lint: tests textwrap/src/word_separators.rs`)
- **Lint issues:** 2

### 8. refill

- **Target:** `textwrap.Refill [PROVENANCE-FALLBACK]`
- **Similarity:** 0.73
- **Dependents:** 0
- **Priority Score:** 2202.7
- **Functions:** 22/22 matched (target 23)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 20/20 matched
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `textwrap/src/refill.rs` vs expected `refill.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `tests:textwrap/src/refill.rs` vs expected `refill.rs`
- **Proposed provenance header:** `// port-lint: source refill.rs` (current: `// port-lint: source textwrap/src/refill.rs`)
- **Proposed provenance header:** `// port-lint: tests refill.rs` (current: `// port-lint: tests textwrap/src/refill.rs`)
- **Lint issues:** 2

### 9. fill

- **Target:** `textwrap.Fill [PROVENANCE-FALLBACK]`
- **Similarity:** 0.80
- **Dependents:** 0
- **Priority Score:** 2102.0
- **Functions:** 21/21 matched (target 22)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 18/18 matched
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `textwrap/src/fill.rs` vs expected `fill.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `tests:textwrap/src/fill.rs` vs expected `fill.rs`
- **Proposed provenance header:** `// port-lint: source fill.rs` (current: `// port-lint: source textwrap/src/fill.rs`)
- **Proposed provenance header:** `// port-lint: tests fill.rs` (current: `// port-lint: tests textwrap/src/fill.rs`)
- **Lint issues:** 2

### 10. core

- **Target:** `textwrap.Core [PROVENANCE-FALLBACK]`
- **Similarity:** 0.60
- **Dependents:** 0
- **Priority Score:** 1904.0
- **Functions:** 16/16 matched (target 23)
- **Missing functions:** _none_
- **Types:** 3/3 matched (target 4)
- **Missing types:** _none_
- **Tests:** 6/6 matched
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `textwrap/src/core.rs` vs expected `core.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `tests:textwrap/src/core.rs` vs expected `core.rs`
- **Proposed provenance header:** `// port-lint: source core.rs` (current: `// port-lint: source textwrap/src/core.rs`)
- **Proposed provenance header:** `// port-lint: tests core.rs` (current: `// port-lint: tests textwrap/src/core.rs`)
- **Lint issues:** 2

### 11. indentation

- **Target:** `textwrap.Indentation [PROVENANCE-FALLBACK]`
- **Similarity:** 0.50
- **Dependents:** 0
- **Priority Score:** 1505.0
- **Functions:** 15/15 matched (target 16)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 13/13 matched
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `textwrap/src/indentation.rs` vs expected `indentation.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `tests:textwrap/src/indentation.rs` vs expected `indentation.rs`
- **Proposed provenance header:** `// port-lint: source indentation.rs` (current: `// port-lint: source textwrap/src/indentation.rs`)
- **Proposed provenance header:** `// port-lint: tests indentation.rs` (current: `// port-lint: tests textwrap/src/indentation.rs`)
- **Lint issues:** 2

### 12. word_splitters

- **Target:** `textwrap.WordSplitters [PROVENANCE-FALLBACK]`
- **Similarity:** 0.74
- **Dependents:** 0
- **Priority Score:** 1002.6
- **Functions:** 9/9 matched (target 17)
- **Missing functions:** _none_
- **Types:** 1/1 matched (target 5)
- **Missing types:** _none_
- **Tests:** 6/6 matched
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `textwrap/src/word_splitters.rs` vs expected `word_splitters.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `tests:textwrap/src/word_splitters.rs` vs expected `word_splitters.rs`
- **Proposed provenance header:** `// port-lint: source word_splitters.rs` (current: `// port-lint: source textwrap/src/word_splitters.rs`)
- **Proposed provenance header:** `// port-lint: tests word_splitters.rs` (current: `// port-lint: tests textwrap/src/word_splitters.rs`)
- **Lint issues:** 2

### 13. columns

- **Target:** `textwrap.Columns [PROVENANCE-FALLBACK]`
- **Similarity:** 0.61
- **Dependents:** 0
- **Priority Score:** 703.9
- **Functions:** 7/7 matched (target 8)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 6/6 matched
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `textwrap/src/columns.rs` vs expected `columns.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `tests:textwrap/src/columns.rs` vs expected `columns.rs`
- **Proposed provenance header:** `// port-lint: source columns.rs` (current: `// port-lint: source textwrap/src/columns.rs`)
- **Proposed provenance header:** `// port-lint: tests columns.rs` (current: `// port-lint: tests textwrap/src/columns.rs`)
- **Lint issues:** 2

### 14. fuzzing

- **Target:** `textwrap.Fuzzing [PROVENANCE-FALLBACK]`
- **Similarity:** 0.84
- **Dependents:** 0
- **Priority Score:** 301.6
- **Functions:** 3/3 matched (target 6)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `textwrap/src/fuzzing.rs` vs expected `fuzzing.rs`
- **Proposed provenance header:** `// port-lint: source fuzzing.rs` (current: `// port-lint: source textwrap/src/fuzzing.rs`)
- **Lint issues:** 1

### 15. lib

- **Target:** `textwrap.Lib [STUB] [PROVENANCE-FALLBACK]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 10.0
- **Functions:** 0/0 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched
- **Missing types:** _none_
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `textwrap/src/lib.rs` vs expected `lib.rs`
- **Proposed provenance header:** `// port-lint: source lib.rs` (current: `// port-lint: source textwrap/src/lib.rs`)
- **Lint issues:** 1

## Success Criteria

For each file to be considered "complete":
- **Similarity ≥ 0.85** (Excellent threshold)
- All public APIs ported
- All tests ported
- Documentation ported
- port-lint header present

