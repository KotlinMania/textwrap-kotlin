# Immediate Actions - High-Value Files

Based on AST analysis, here are the concrete next steps.

## Summary

- **Files Present:** 15/17 (88.2%)
- **Function parity:** 183/200 matched (target 280) — 91.5%
- **Class/type parity:** 13/15 matched (target 42) — 86.7%
- **Combined symbol parity:** 196/215 matched (target 322) — 91.2%
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

### 1. textwrap.options

- **Target:** `textwrap.Options`
- **Similarity:** 0.75
- **Dependents:** 4
- **Priority Score:** 4001202.5
- **Functions:** 11/11 matched (target 13)
- **Missing functions:** _none_
- **Types:** 1/1 matched (target 2)
- **Missing types:** _none_
- **Tests:** 1/1 matched

### 2. textwrap.wrap

- **Target:** `textwrap.Wrap`
- **Similarity:** 0.72
- **Dependents:** 1
- **Priority Score:** 1004802.8
- **Functions:** 48/48 matched (target 49)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 45/45 matched

### 3. textwrap.line_ending

- **Target:** `textwrap.LineEnding`
- **Similarity:** 0.39
- **Dependents:** 1
- **Priority Score:** 1000806.1
- **Functions:** 5/5 matched (target 7)
- **Missing functions:** _none_
- **Types:** 3/3 matched (target 5)
- **Missing types:** _none_
- **Tests:** 3/3 matched

### 4. textwrap.termwidth

- **Target:** `textwrap.Termwidth`
- **Similarity:** 0.44
- **Dependents:** 1
- **Priority Score:** 1000205.6
- **Functions:** 2/2 matched (target 3)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 5. textwrap.wrap_algorithms

- **Target:** `textwrap.WrapAlgorithms`
- **Similarity:** 0.80
- **Dependents:** 0
- **Priority Score:** 41202.0
- **Functions:** 7/10 matched (target 24)
- **Missing functions:** `width`, `whitespace_width`, `penalty_width`
- **Types:** 1/2 matched (target 7)
- **Missing types:** `WrapAlgorithm`
- **Tests:** 1/4 matched

### 6. wrap_algorithms.optimal_fit

- **Target:** `wrapalgorithms.OptimalFit`
- **Similarity:** 0.61
- **Dependents:** 0
- **Priority Score:** 31503.9
- **Functions:** 8/11 matched (target 21)
- **Missing functions:** `width`, `whitespace_width`, `penalty_width`
- **Types:** 4/4 matched (target 8)
- **Missing types:** _none_
- **Tests:** 3/6 matched

### 7. textwrap.word_separators

- **Target:** `textwrap.WordSeparators`
- **Similarity:** 0.68
- **Dependents:** 0
- **Priority Score:** 21103.2
- **Functions:** 9/10 matched (target 48)
- **Missing functions:** `to_words`
- **Types:** 0/1 matched (target 4)
- **Missing types:** `WordSeparator`
- **Tests:** 3/4 matched

### 8. textwrap.refill

- **Target:** `textwrap.Refill`
- **Similarity:** 0.73
- **Dependents:** 0
- **Priority Score:** 2202.7
- **Functions:** 22/22 matched (target 23)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 20/20 matched

### 9. textwrap.fill

- **Target:** `textwrap.Fill`
- **Similarity:** 0.80
- **Dependents:** 0
- **Priority Score:** 2102.0
- **Functions:** 21/21 matched (target 22)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 18/18 matched

### 10. textwrap.core

- **Target:** `textwrap.Core`
- **Similarity:** 0.60
- **Dependents:** 0
- **Priority Score:** 1904.0
- **Functions:** 16/16 matched (target 23)
- **Missing functions:** _none_
- **Types:** 3/3 matched (target 4)
- **Missing types:** _none_
- **Tests:** 6/6 matched

### 11. textwrap.indentation

- **Target:** `textwrap.Indentation`
- **Similarity:** 0.50
- **Dependents:** 0
- **Priority Score:** 1505.0
- **Functions:** 15/15 matched (target 16)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 13/13 matched

### 12. textwrap.word_splitters

- **Target:** `textwrap.WordSplitters`
- **Similarity:** 0.74
- **Dependents:** 0
- **Priority Score:** 1002.6
- **Functions:** 9/9 matched (target 17)
- **Missing functions:** _none_
- **Types:** 1/1 matched (target 5)
- **Missing types:** _none_
- **Tests:** 6/6 matched

### 13. textwrap.columns

- **Target:** `textwrap.Columns`
- **Similarity:** 0.61
- **Dependents:** 0
- **Priority Score:** 703.9
- **Functions:** 7/7 matched (target 8)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 6/6 matched

### 14. textwrap.fuzzing

- **Target:** `textwrap.Fuzzing`
- **Similarity:** 0.84
- **Dependents:** 0
- **Priority Score:** 301.6
- **Functions:** 3/3 matched (target 6)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 15. textwrap.lib

- **Target:** `textwrap.Lib [STUB]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 10.0
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

