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

package kwasm.format.text.type

import kwasm.ast.type.Limits
import kwasm.format.ParseException
import kwasm.format.text.ParseResult
import kwasm.format.text.parseLiteral
import kwasm.format.text.token.Token

/**
 * From [the spec](]https://webassembly.github.io/spec/core/text/types.html#limits):
 *
 * ```
 *   limits ::=  n:u32        => {min n, max ϵ}
 *           |   n:u32  m:u32 => {min n, max m}
 * ```
 */
@OptIn(ExperimentalUnsignedTypes::class)
fun List<Token>.parseLimits(startingIndex: Int): ParseResult<Limits> {
    var currentIndex = startingIndex
    val min = parseLiteral(currentIndex, UInt::class)
    currentIndex += min.parseLength
    val max = try {
        parseLiteral(currentIndex, UInt::class)
    } catch (e: ParseException) {
        null
    }
    currentIndex += max?.parseLength ?: 0

    return ParseResult(
        Limits(
            min.astNode.value.toLong(),
            max?.astNode?.value?.toLong()
        ),
        currentIndex - startingIndex
    )
}
