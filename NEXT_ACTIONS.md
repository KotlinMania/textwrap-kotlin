# Immediate Actions - High-Value Files

Based on AST analysis, here are the concrete next steps.

## Summary

- **Files Present:** 14/15 (93.3%)
- **Function parity:** 183/193 matched (target 279) — 94.8%
- **Class/type parity:** 15/15 matched (target 45) — 100.0%
- **Combined symbol parity:** 198/208 matched (target 324) — 95.2%
- **Average inline-code cosine:** 0.23 (function body across 14 matched files)
- **Average documentation cosine:** 0.76 (doc text across 14 matched files)
- **Cheat-zeroed Files:** 0
- **Critical Issues:** 14 files with <0.60 function similarity
- **Needs Review:** 0 files with 0.60-0.84 function similarity
- **Excellent:** 0 files with >=0.85 function similarity

## Priority 1: Fix Incomplete High-Dependency Files

No incomplete high-dependency files detected.

## Priority 2: Port Missing High-Value Files

Critical missing files (>10 dependencies):

No missing high-value files detected.

## Detailed Work Items

Every matched file is listed below with function and type symbol parity.

### 1. options

- **Target:** `textwrap.Options`
- **Similarity:** 0.19
- **Dependents:** 4
- **Priority Score:** 4011308.0
- **Functions:** 11/12 matched (target 13)
- **Missing functions:** `from`
- **Types:** 1/1 matched (target 2)
- **Missing types:** _none_
- **Tests:** 1/1 matched

### 2. wrap

- **Target:** `textwrap.Wrap`
- **Similarity:** 0.21
- **Dependents:** 1
- **Priority Score:** 1004807.9
- **Functions:** 48/48 matched (target 49)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 45/45 matched

### 3. line_ending

- **Target:** `textwrap.LineEnding`
- **Similarity:** 0.11
- **Dependents:** 1
- **Priority Score:** 1000808.9
- **Functions:** 5/5 matched (target 7)
- **Missing functions:** _none_
- **Types:** 3/3 matched (target 4)
- **Missing types:** _none_
- **Tests:** 3/3 matched

### 4. termwidth

- **Target:** `textwrap.Termwidth`
- **Similarity:** 0.24
- **Dependents:** 1
- **Priority Score:** 1000207.6
- **Functions:** 2/2 matched (target 3)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 5. wrap_algorithms.optimal_fit

- **Target:** `wrapalgorithms.OptimalFit`
- **Similarity:** 0.12
- **Dependents:** 0
- **Priority Score:** 41608.8
- **Functions:** 8/12 matched (target 21)
- **Missing functions:** `new`, `width`, `whitespace_width`, `penalty_width`
- **Types:** 4/4 matched (target 7)
- **Missing types:** _none_
- **Tests:** 3/6 matched

### 6. wrap_algorithms

- **Target:** `textwrap.WrapAlgorithms`
- **Similarity:** 0.20
- **Dependents:** 0
- **Priority Score:** 31208.0
- **Functions:** 7/10 matched (target 24)
- **Missing functions:** `width`, `whitespace_width`, `penalty_width`
- **Types:** 2/2 matched (target 9)
- **Missing types:** _none_
- **Tests:** 1/4 matched

### 7. core

- **Target:** `textwrap.Core`
- **Similarity:** 0.20
- **Dependents:** 0
- **Priority Score:** 12008.0
- **Functions:** 16/17 matched (target 23)
- **Missing functions:** `ch_width`
- **Types:** 3/3 matched (target 4)
- **Missing types:** _none_
- **Tests:** 6/6 matched

### 8. word_separators

- **Target:** `textwrap.WordSeparators`
- **Similarity:** 0.13
- **Dependents:** 0
- **Priority Score:** 11108.7
- **Functions:** 9/10 matched (target 48)
- **Missing functions:** `to_words`
- **Types:** 1/1 matched (target 6)
- **Missing types:** _none_
- **Tests:** 3/4 matched

### 9. refill

- **Target:** `textwrap.Refill`
- **Similarity:** 0.39
- **Dependents:** 0
- **Priority Score:** 2206.1
- **Functions:** 22/22 matched (target 23)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 20/20 matched

### 10. fill

- **Target:** `textwrap.Fill`
- **Similarity:** 0.39
- **Dependents:** 0
- **Priority Score:** 2106.1
- **Functions:** 21/21 matched (target 22)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 18/18 matched

### 11. indentation

- **Target:** `textwrap.Indentation`
- **Similarity:** 0.34
- **Dependents:** 0
- **Priority Score:** 1506.6
- **Functions:** 15/15 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 13/13 matched

### 12. word_splitters

- **Target:** `textwrap.WordSplitters`
- **Similarity:** 0.14
- **Dependents:** 0
- **Priority Score:** 1008.6
- **Functions:** 9/9 matched (target 17)
- **Missing functions:** _none_
- **Types:** 1/1 matched (target 6)
- **Missing types:** _none_
- **Tests:** 6/6 matched

### 13. columns

- **Target:** `textwrap.Columns`
- **Similarity:** 0.23
- **Dependents:** 0
- **Priority Score:** 707.7
- **Functions:** 7/7 matched (target 8)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 6/6 matched

### 14. fuzzing

- **Target:** `textwrap.Fuzzing`
- **Similarity:** 0.32
- **Dependents:** 0
- **Priority Score:** 306.8
- **Functions:** 3/3 matched (target 6)
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

## Reexport / Wiring Modules

These files match `reexport_modules` patterns in `.ast_distance_config.json`. They are filtered out of
normal priority and missing-file ladders because they are wiring
modules, not direct logic ports. Consult them for call-site routing;
do not treat them as the next implementation target by default.

### Missing

| Source | Expected target | Deps | Source path | Expected path |
|--------|-----------------|------|-------------|---------------|
| `lib` | `Lib` | 0 | `lib.rs` | `Lib.kt` |

