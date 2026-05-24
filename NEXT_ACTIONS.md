# Immediate Actions - High-Value Files

Based on AST analysis, here are the concrete next steps.

## Summary

- **Files Present:** 1/15 (6.7%)
- **Function parity:** 5/193 matched (target 7) — 2.6%
- **Class/type parity:** 2/15 matched (target 4) — 13.3%
- **Combined symbol parity:** 7/208 matched (target 11) — 3.4%
- **Average inline-code cosine:** 0.39 (function body across 1 matched files)
- **Average documentation cosine:** 0.94 (doc text across 1 matched files)
- **Cheat-zeroed Files:** 0
- **Critical Issues:** 1 files with <0.60 function similarity

## Priority 1: Fix Incomplete High-Dependency Files

No incomplete high-dependency files detected.

## Priority 2: Port Missing High-Value Files

Critical missing files (>10 dependencies):

No missing high-value files detected.

## Detailed Work Items

Every matched file is listed below with function and type symbol parity.

### 1. line_ending

- **Target:** `textwrap.LineEnding`
- **Similarity:** 0.39
- **Dependents:** 1
- **Priority Score:** 1010806.1
- **Functions:** 5/5 matched (target 7)
- **Missing functions:** _none_
- **Types:** 2/3 matched (target 4)
- **Missing types:** `Item`
- **Tests:** 3/3 matched

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

