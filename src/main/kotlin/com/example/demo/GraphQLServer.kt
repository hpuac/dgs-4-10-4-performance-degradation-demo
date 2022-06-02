package com.example.demo

import com.example.demo.types.MathResponse
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument

@DgsComponent
class GraphQLServer {
    @DgsQuery
    fun math(@InputArgument(name = "numbers") numbers: List<Int>): List<MathResponse> {
        return numbers.map {
            MathResponse(
                number = it,
                doubled = it * 2,
                halved = it / 2,
                incremented = it + 1,
                decremented = it - 1
            )
        }
    }
}
