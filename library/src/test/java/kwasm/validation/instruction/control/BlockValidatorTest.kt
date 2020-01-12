/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

package kwasm.validation.instruction.control

import com.google.common.truth.Truth.assertThat
import kwasm.ParseRule
import kwasm.ast.type.ValueType
import kwasm.validation.ValidationContext.Companion.EMPTY_FUNCTION_BODY
import kwasm.validation.ValidationException
import kwasm.validation.instruction.validate
import org.junit.Assert.assertThrows
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class BlockValidatorTest {
    @get:Rule
    val parser = ParseRule()

    @Test
    fun invalid_ifInstructions_finishWithWrongType_noneExpected() = parser.with {
        assertThrows(ValidationException::class.java) {
            "(block (i32.const 1))".parseInstructions().validate(EMPTY_FUNCTION_BODY)
        }
    }

    @Test
    fun invalid_ifInstructions_finishWithWrongType() = parser.with {
        assertThrows(ValidationException::class.java) {
            "(block (result i64) (i32.const 1))".parseInstructions().validate(EMPTY_FUNCTION_BODY)
        }
    }

    @Test
    fun invalid_ifInstructions_finishWithWrongType_expected_nonePresent() = parser.with {
        assertThrows(ValidationException::class.java) {
            "(block (result i64))".parseInstructions().validate(EMPTY_FUNCTION_BODY)
        }
    }

    @Test
    fun valid_noExpectedType_noBreaks_noLabel() = parser.with {
        val result = "(block (nop))".parseInstructions().validate(EMPTY_FUNCTION_BODY)
        assertThat(result.stack).isEmpty()
    }

    @Test
    fun valid_expectedType_noBreaks_noLabel() = parser.with {
        val result =
            """
            (block (result i32) 
                (i32.add (i32.const 1) (i32.const 2))
            )
            """.trimIndent().parseInstructions().validate(EMPTY_FUNCTION_BODY)
        assertThat(result.stack).containsExactly(ValueType.I32)
    }

    @Test
    fun valid_expectedType_break_noLabel() = parser.with {
        val result =
            """
            (block (result i32)
                (i32.const 0)
                (br 0)
            )
            """.trimIndent().parseInstructions().validate(EMPTY_FUNCTION_BODY)
        assertThat(result.stack).containsExactly(ValueType.I32)
    }

    @Test
    fun valid_expectedType_break_label() = parser.with {
        val result =
            """
            (block $0 (result i32)
                (i32.const 0)
                (br $0)
            )
            """.trimIndent().parseInstructions().validate(EMPTY_FUNCTION_BODY)
        assertThat(result.stack).containsExactly(ValueType.I32)
    }
}
