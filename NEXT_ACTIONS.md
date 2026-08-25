# Immediate Actions - High-Value Files

Based on AST analysis, here are the concrete next steps.

## Summary

- **Files Present:** 15/15 (100.0%)
- **Function parity:** 183/190 matched (target 278) — 96.3%
- **Class/type parity:** 15/15 matched (target 48) — 100.0%
- **Combined symbol parity:** 198/205 matched (target 326) — 96.6%
- **Average inline-code cosine:** 0.68 (function body across 15 matched files)
- **Average documentation cosine:** 0.66 (doc text across 15 matched files)
- **Cheat-zeroed Files:** 0
- **Critical Issues:** 3 files with <0.60 function similarity

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
- **Similarity:** 0.72
- **Dependents:** 1
- **Priority Score:** 1004802.8
- **Functions:** 48/48 matched (target 49)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 45/45 matched

### 3. line_ending

- **Target:** `textwrap.LineEnding`
- **Similarity:** 0.39
- **Dependents:** 1
- **Priority Score:** 1000806.1
- **Functions:** 5/5 matched (target 7)
- **Missing functions:** _none_
- **Types:** 3/3 matched (target 5)
- **Missing types:** _none_
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
- **Similarity:** 0.61
- **Dependents:** 0
- **Priority Score:** 31503.9
- **Functions:** 8/11 matched (target 20)
- **Missing functions:** `width`, `whitespace_width`, `penalty_width`
- **Types:** 4/4 matched (target 8)
- **Missing types:** _none_
- **Tests:** 3/6 matched

### 6. wrap_algorithms

- **Target:** `textwrap.WrapAlgorithms`
- **Similarity:** 0.80
- **Dependents:** 0
- **Priority Score:** 31202.0
- **Functions:** 7/10 matched (target 26)
- **Missing functions:** `width`, `whitespace_width`, `penalty_width`
- **Types:** 2/2 matched (target 9)
- **Missing types:** _none_
- **Tests:** 1/4 matched

### 7. word_separators

- **Target:** `textwrap.WordSeparators`
- **Similarity:** 0.68
- **Dependents:** 0
- **Priority Score:** 11103.2
- **Functions:** 9/10 matched (target 47)
- **Missing functions:** `to_words`
- **Types:** 1/1 matched (target 6)
- **Missing types:** _none_
- **Tests:** 3/4 matched

### 8. refill

- **Target:** `textwrap.Refill`
- **Similarity:** 0.73
- **Dependents:** 0
- **Priority Score:** 2202.7
- **Functions:** 22/22 matched (target 23)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 20/20 matched

### 9. fill

- **Target:** `textwrap.Fill`
- **Similarity:** 0.80
- **Dependents:** 0
- **Priority Score:** 2102.0
- **Functions:** 21/21 matched (target 22)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 18/18 matched

### 10. core

- **Target:** `textwrap.Core`
- **Similarity:** 0.60
- **Dependents:** 0
- **Priority Score:** 1904.0
- **Functions:** 16/16 matched (target 22)
- **Missing functions:** _none_
- **Types:** 3/3 matched (target 4)
- **Missing types:** _none_
- **Tests:** 6/6 matched

### 11. indentation

- **Target:** `textwrap.Indentation`
- **Similarity:** 0.50
- **Dependents:** 0
- **Priority Score:** 1505.0
- **Functions:** 15/15 matched (target 16)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 13/13 matched

### 12. word_splitters

- **Target:** `textwrap.WordSplitters`
- **Similarity:** 0.74
- **Dependents:** 0
- **Priority Score:** 1002.6
- **Functions:** 9/9 matched (target 16)
- **Missing functions:** _none_
- **Types:** 1/1 matched (target 6)
- **Missing types:** _none_
- **Tests:** 6/6 matched

### 13. columns

- **Target:** `textwrap.Columns`
- **Similarity:** 0.61
- **Dependents:** 0
- **Priority Score:** 703.9
- **Functions:** 7/7 matched (target 8)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 6/6 matched

### 14. fuzzing

- **Target:** `textwrap.Fuzzing`
- **Similarity:** 0.84
- **Dependents:** 0
- **Priority Score:** 301.6
- **Functions:** 3/3 matched (target 6)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 15. lib

- **Target:** `textwrap.Lib`
- **Similarity:** 1.00
- **Dependents:** 0
- **Priority Score:** 0.0
- **Functions:** 0/0 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

## Success Criteria

For each file to be considered "complete":
- **Similarity ≥ 0.85** (Excellent threshold)
- All public APIs ported
- All tests ported
- Documentation ported
- port-lint header present

