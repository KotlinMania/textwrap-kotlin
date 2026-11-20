# Immediate Actions - High-Value Files

Based on AST analysis, here are the concrete next steps.

## Summary

- **Files Present:** 14/15 (93.3%)
- **Function parity:** 58/193 matched (target 106) — 30.1%
- **Class/type parity:** 13/15 matched (target 29) — 86.7%
- **Combined symbol parity:** 71/208 matched (target 135) — 34.1%
- **Average inline-code cosine:** 0.11 (function body across 14 matched files)
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

- **Target:** `Options`
- **Similarity:** 0.16
- **Dependents:** 4
- **Priority Score:** 4021308.5
- **Functions:** 10/12 matched (target 10)
- **Missing functions:** `from`, `options_agree_with_usize`
- **Types:** 1/1 matched
- **Missing types:** _none_
- **Tests:** 0/1 matched

### 2. wrap

- **Target:** `Wrap`
- **Similarity:** 0.03
- **Dependents:** 1
- **Priority Score:** 1454809.8
- **Functions:** 3/48 matched (target 4)
- **Missing functions:** `no_wrap`, `wrap_simple`, `to_be_or_not`, `multiple_words_on_first_line`, `long_word`, `long_words`, `max_width`, `leading_whitespace`, `leading_whitespace_empty_first_line`, `trailing_whitespace`, `issue_99`, `issue_129`, `wide_character_handling`, `indent_empty_line`, `indent_single_line`, `indent_first_emoji`, `indent_multiple_lines`, `only_initial_indent_multiple_lines`, `only_subsequent_indent_multiple_lines`, `indent_break_words`, `initial_indent_break_words`, `hyphens`, `trailing_hyphen`, `multiple_hyphens`, `hyphens_flag`, `repeated_hyphens`, `hyphens_alphanumeric`, `hyphens_non_alphanumeric`, `multiple_splits`, `forced_split`, `multiple_unbroken_words_issue_193`, `very_narrow_lines_issue_193`, `simple_hyphens`, `no_hyphenation`, `auto_hyphenation_double_hyphenation`, `auto_hyphenation_issue_158`, `split_len_hyphenation`, `borrowed_lines`, `auto_hyphenation_with_hyphen`, `break_words`, `break_words_wide_characters`, `break_words_zero_width`, `break_long_first_word`, `wrap_preserves_line_breaks_trims_whitespace`, `wrap_colored_text`
- **Types:** 0/0 matched
- **Missing types:** _none_
- **Tests:** 0/45 matched

### 3. line_ending

- **Target:** `LineEnding`
- **Similarity:** 0.06
- **Dependents:** 1
- **Priority Score:** 1030809.4
- **Functions:** 2/5 matched (target 4)
- **Missing functions:** `non_empty_lines_full_case`, `non_empty_lines_new_lines_only`, `non_empty_lines_no_input`
- **Types:** 3/3 matched
- **Missing types:** _none_
- **Tests:** 0/3 matched

### 4. termwidth

- **Target:** `Termwidth`
- **Similarity:** 0.24
- **Dependents:** 1
- **Priority Score:** 1000207.6
- **Functions:** 2/2 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched
- **Missing types:** _none_

### 5. refill

- **Target:** `Refill`
- **Similarity:** 0.03
- **Dependents:** 0
- **Priority Score:** 202209.7
- **Functions:** 2/22 matched (target 3)
- **Missing functions:** `unfill_simple`, `unfill_no_new_line`, `unfill_simple_crlf`, `unfill_mixed_new_lines`, `test_unfill_consecutive_different_prefix`, `unfill_trailing_newlines`, `unfill_mixed_trailing_newlines`, `unfill_trailing_crlf`, `unfill_initial_indent`, `unfill_differing_indents`, `unfill_list_item`, `unfill_multiple_char_prefix`, `unfill_block_quote`, `unfill_only_prefixes_issue_466`, `unfill_trailing_newlines_issue_466`, `unfill_whitespace`, `refill_convert_lf_to_crlf`, `refill_convert_crlf_to_lf`, `refill_convert_mixed_newlines`, `refill_defaults_to_lf`
- **Types:** 0/0 matched
- **Missing types:** _none_
- **Tests:** 0/20 matched

### 6. fill

- **Target:** `Fill`
- **Similarity:** 0.05
- **Dependents:** 0
- **Priority Score:** 182109.5
- **Functions:** 3/21 matched (target 4)
- **Missing functions:** `fill_simple`, `fill_unicode_boundary`, `non_breaking_space`, `non_breaking_hyphen`, `fill_preserves_line_breaks_trims_whitespace`, `preserve_line_breaks`, `break_words_line_breaks`, `break_words_empty_lines`, `fill_inplace_empty`, `fill_inplace_simple`, `fill_inplace_multiple_lines`, `fill_inplace_long_word`, `fill_inplace_no_hyphen_splitting`, `fill_inplace_newlines`, `fill_inplace_newlines_reset_line_width`, `fill_inplace_leading_whitespace`, `fill_inplace_trailing_whitespace`, `fill_inplace_interior_whitespace`
- **Types:** 0/0 matched
- **Missing types:** _none_
- **Tests:** 0/18 matched

### 7. indentation

- **Target:** `Indentation`
- **Similarity:** 0.04
- **Dependents:** 0
- **Priority Score:** 131509.6
- **Functions:** 2/15 matched (target 2)
- **Missing functions:** `indent_empty`, `indent_nonempty`, `indent_empty_line`, `dedent_empty`, `dedent_multi_line`, `dedent_empty_line`, `dedent_blank_line`, `dedent_whitespace_line`, `dedent_mixed_whitespace`, `dedent_tabbed_whitespace`, `dedent_mixed_tabbed_whitespace`, `dedent_mixed_tabbed_whitespace2`, `dedent_preserve_no_terminating_newline`
- **Types:** 0/0 matched
- **Missing types:** _none_
- **Tests:** 0/13 matched

### 8. wrap_algorithms.optimal_fit

- **Target:** `OptimalFit`
- **Similarity:** 0.08
- **Dependents:** 0
- **Priority Score:** 81609.2
- **Functions:** 5/12 matched (target 15)
- **Missing functions:** `new`, `width`, `whitespace_width`, `penalty_width`, `wrap_fragments_with_infinite_widths`, `wrap_fragments_with_huge_widths`, `wrap_fragments_with_large_widths`
- **Types:** 3/4 matched (target 5)
- **Missing types:** `Word`
- **Tests:** 0/6 matched

### 9. core

- **Target:** `Core`
- **Similarity:** 0.14
- **Dependents:** 0
- **Priority Score:** 72008.6
- **Functions:** 10/17 matched (target 14)
- **Missing functions:** `ch_width`, `skip_ansi_escape_sequence_works`, `emojis_have_correct_width`, `display_width_works`, `display_width_narrow_emojis`, `display_width_narrow_emojis_variant_selector`, `display_width_emojis`
- **Types:** 3/3 matched
- **Missing types:** _none_
- **Tests:** 0/6 matched

### 10. word_splitters

- **Target:** `WordSplitters`
- **Similarity:** 0.04
- **Dependents:** 0
- **Priority Score:** 61009.6
- **Functions:** 3/9 matched
- **Missing functions:** `split_words_no_words`, `split_words_empty_word`, `split_words_single_word`, `split_words_hyphen_splitter`, `split_words_no_hyphenation`, `split_words_adds_penalty`
- **Types:** 1/1 matched (target 5)
- **Missing types:** _none_
- **Tests:** 0/6 matched

### 11. columns

- **Target:** `Columns`
- **Similarity:** 0.06
- **Dependents:** 0
- **Priority Score:** 60709.4
- **Functions:** 1/7 matched (target 2)
- **Missing functions:** `wrap_columns_empty_text`, `wrap_columns_single_column`, `wrap_columns_uneven_columns`, `wrap_columns_with_emojis`, `wrap_columns_big_gaps`, `wrap_columns_panic_with_zero_columns`
- **Types:** 0/0 matched
- **Missing types:** _none_
- **Tests:** 0/6 matched

### 12. wrap_algorithms

- **Target:** `WrapAlgorithms`
- **Similarity:** 0.16
- **Dependents:** 0
- **Priority Score:** 51208.4
- **Functions:** 6/10 matched (target 15)
- **Missing functions:** `width`, `whitespace_width`, `penalty_width`, `wrap_string_longer_than_f64`
- **Types:** 1/2 matched (target 6)
- **Missing types:** `Word`
- **Tests:** 0/4 matched

### 13. word_separators

- **Target:** `WordSeparators`
- **Similarity:** 0.10
- **Dependents:** 0
- **Priority Score:** 41109.0
- **Functions:** 6/10 matched (target 16)
- **Missing functions:** `to_words`, `find_words_colored_text`, `find_words_color_inside_word`, `word_separator_new`
- **Types:** 1/1 matched (target 5)
- **Missing types:** _none_
- **Tests:** 0/4 matched

### 14. fuzzing

- **Target:** `Fuzzing`
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

