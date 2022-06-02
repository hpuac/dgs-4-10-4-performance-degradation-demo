# DGS v4.10.4 performance degradation demo

The purpose of this repository is to demonstrate a performance degradation that
was introduced in DGS v4.10.4.  
This degradation only happens if the `graphql-dgs-spring-boot-micrometer`
dependency is added.

## Steps to reproduce:

1. Run the `generateJava` gradle task
2. Start the [DemoApplication](./src/main/kotlin/com/example/demo/DemoApplication.kt)
3. Run the [request.sh](./request.sh) test file and note down the response times
4. Stop the [DemoApplication](./src/main/kotlin/com/example/demo/DemoApplication.kt)
5. Decrease the DGS version in the [build.gradle.kts](./build.gradle.kts)
6. Repeat step 2 and 3.

## My measured latencies

In the following table I averaged the latencies from 3 requests.

| DGS                                          | v4.10.2 | v4.10.3 | v4.10.4 | v5.0.0 | v5.0.1 |
|----------------------------------------------|---------|---------|---------|--------|--------|
| Without `graphql-dgs-spring-boot-micrometer` | 101ms   | 111ms   | 104ms   | 102ms  | 106ms  |
| With `graphql-dgs-spring-boot-micrometer`    | 145ms   | 147ms   | 842ms   | 867ms  | 864ms  |

It's clearly visible that there is a huge latency increase with
`graphql-dgs-spring-boot-micrometer` enabled when updating from
v4.10.3 to v4.10.4.
