# Immediate Actions - High-Value Files

Based on AST analysis, here are the concrete next steps.

## Summary

- **Files Present:** 15/15 (100.0%)
- **Function parity:** 164/190 matched (target 231) — 86.3%
- **Class/type parity:** 10/15 matched (target 39) — 66.7%
- **Combined symbol parity:** 174/205 matched (target 270) — 84.9%
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

- **Target:** `textwrap.Options`
- **Similarity:** 0.75
- **Dependents:** 4
- **Priority Score:** 4001202.5
- **Functions:** 11/11 matched (target 13)
- **Missing functions:** _none_
- **Types:** 1/1 matched (target 2)
- **Missing types:** _none_
- **Tests:** 1/1 matched

### 2. wrap

- **Target:** `textwrap.Wrap`
- **Similarity:** 0.70
- **Dependents:** 1
- **Priority Score:** 1024803.1
- **Functions:** 46/48 matched
- **Missing functions:** `borrowed_lines`, `break_words`
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 43/45 matched

### 3. line_ending

- **Target:** `textwrap.LineEnding`
- **Similarity:** 0.39
- **Dependents:** 1
- **Priority Score:** 1010806.1
- **Functions:** 5/5 matched (target 7)
- **Missing functions:** _none_
- **Types:** 2/3 matched (target 4)
- **Missing types:** `Item`
- **Tests:** 3/3 matched

### 4. termwidth

- **Target:** `textwrap.Termwidth`
- **Similarity:** 0.44
- **Dependents:** 1
- **Priority Score:** 1000205.6
- **Functions:** 2/2 matched (target 3)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 5. wrap_algorithms.optimal_fit

- **Target:** `wrapalgorithms.OptimalFit`
- **Similarity:** 0.20
- **Dependents:** 0
- **Priority Score:** 91508.0
- **Functions:** 4/11 matched (target 5)
- **Missing functions:** `fmt`, `width`, `whitespace_width`, `penalty_width`, `wrap_fragments_with_infinite_widths`, `wrap_fragments_with_huge_widths`, `wrap_fragments_with_large_widths`
- **Types:** 2/4 matched
- **Missing types:** `OverflowError`, `Word`
- **Tests:** 0/6 matched

### 6. word_separators

- **Target:** `textwrap.WordSeparators`
- **Similarity:** 0.24
- **Dependents:** 0
- **Priority Score:** 71107.6
- **Functions:** 3/10 matched (target 26)
- **Missing functions:** `eq`, `find_words_ascii_space`, `strip_ansi_escape_sequences`, `find_words_unicode_break_properties`, `to_words`, `find_words_colored_text`, `find_words_color_inside_word`
- **Types:** 1/1 matched (target 6)
- **Missing types:** _none_
- **Tests:** 1/4 matched

### 7. wrap_algorithms

- **Target:** `textwrap.WrapAlgorithms`
- **Similarity:** 0.59
- **Dependents:** 0
- **Priority Score:** 61204.1
- **Functions:** 5/10 matched (target 21)
- **Missing functions:** `eq`, `default`, `width`, `whitespace_width`, `penalty_width`
- **Types:** 1/2 matched (target 8)
- **Missing types:** `Word`
- **Tests:** 1/4 matched

### 8. fuzzing

- **Target:** `textwrap.Fuzzing`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 30310.0
- **Functions:** 0/3 matched
- **Missing functions:** `fill_slow_path`, `wrap_single_line`, `wrap_single_line_slow_path`
- **Types:** 0/0 matched
- **Missing types:** _none_

### 9. core

- **Target:** `textwrap.Core`
- **Similarity:** 0.54
- **Dependents:** 0
- **Priority Score:** 21904.6
- **Functions:** 15/16 matched (target 21)
- **Missing functions:** `deref`
- **Types:** 2/3 matched
- **Missing types:** `Target`
- **Tests:** 6/6 matched

### 10. word_splitters

- **Target:** `textwrap.WordSplitters`
- **Similarity:** 0.68
- **Dependents:** 0
- **Priority Score:** 11003.2
- **Functions:** 8/9 matched (target 15)
- **Missing functions:** `eq`
- **Types:** 1/1 matched (target 6)
- **Missing types:** _none_
- **Tests:** 6/6 matched

### 11. refill

- **Target:** `textwrap.Refill`
- **Similarity:** 0.73
- **Dependents:** 0
- **Priority Score:** 2202.7
- **Functions:** 22/22 matched (target 23)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 20/20 matched

### 12. fill

- **Target:** `textwrap.Fill`
- **Similarity:** 0.80
- **Dependents:** 0
- **Priority Score:** 2102.0
- **Functions:** 21/21 matched (target 22)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 18/18 matched

### 13. indentation

- **Target:** `textwrap.Indentation`
- **Similarity:** 0.50
- **Dependents:** 0
- **Priority Score:** 1505.0
- **Functions:** 15/15 matched (target 16)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 13/13 matched

### 14. columns

- **Target:** `textwrap.Columns`
- **Similarity:** 0.61
- **Dependents:** 0
- **Priority Score:** 703.9
- **Functions:** 7/7 matched (target 8)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 6/6 matched

### 15. lib

- **Target:** `textwrap.Lib`
- **Similarity:** 1.00
- **Dependents:** 0
- **Priority Score:** 0.0
- **Functions:** 0/0 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched
- **Missing types:** _none_

## Success Criteria

For each file to be considered "complete":
- **Similarity ≥ 0.85** (Excellent threshold)
- All public APIs ported
- All tests ported
- Documentation ported
- port-lint header present

