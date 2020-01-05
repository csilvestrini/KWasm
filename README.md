# KWasm

KWasm is an Embeddable WebAssembly interpreter for the JVM

[![CircleCI](https://img.shields.io/circleci/build/github/jasonwyatt/KWasm/master?style=flat-square)](https://circleci.com/gh/jasonwyatt/KWasm/tree/master)
[![CII Best Practices Summary](https://img.shields.io/cii/summary/3559?label=cii%20best%20practices&style=flat-square)](https://bestpractices.coreinfrastructure.org/en/projects/3559)
[![Code Climate maintainability](https://img.shields.io/codeclimate/maintainability-percentage/jasonwyatt/KWasm?style=flat-square)](https://codeclimate.com/github/jasonwyatt/KWasm)

## Milestones

The development of KWasm will be done in a series of milestones:

1. ✅ Implement text-based Wasm Parser Capability & AST-generation. (Completed 2020-01-01)
1. Use the text-based Wasm Parser to develop/test the interpretation of the AST.
1. Implement a binary wasm parser with AST-generation.

With optional milestones:

4. Implement a WASM->Kotlin transpiler.
1. Implement a WASM->JVM Bytecode compiler.

Where milestones 2 and 3 are parallelizable.

## License

```
Copyright 2019 Google LLC 

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
