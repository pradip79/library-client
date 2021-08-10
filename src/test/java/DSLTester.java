import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonArray;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import io.pactfoundation.consumer.dsl.LambdaDslJsonBody;

import static io.pactfoundation.consumer.dsl.LambdaDsl.*;


public class DSLTester {
    public static void main(String args[]){
        DslPart body = new PactDslJsonBody()
                .stringType("name", "John Smart")
                .minArrayLike("addresses", 2)
                        .stringType("type", "Home")
                        .stringType("street", "12 Foreshore Road")
                        .stringType("city", "Philadelphia")
                        .stringType("zip", "19101")
                        .stringType("state", "PA")
                    .closeObject()
                .closeArray()
                .array("cars")
                    .stringValue("Ford")
                    .stringValue("BMW")
                    .stringValue("Toyota")
                .closeArray();

        //System.out.println("Body:"+ body);

        LambdaDslJsonBody lambdaBody = newJsonBody((o)-> {
            o.stringType("name", "John Smart");
            o.array("addresses", (p) -> {
                p.object(q -> {
                    q.stringType("type", "home");
                    q.stringType("street", "12 Foreshore Road");
                    q.stringType("city", "Philadelphia");
                });
            });
            o.array("cars", (c) -> {
                c.stringValue("Ford");
                c.stringValue("BMW");
            });
        });

        newJsonArray((rootArray) -> {
            rootArray.array((a) -> a.stringValue("a1").stringValue("a2"));
            rootArray.array((a) -> a.numberValue(1).numberValue(2));
            rootArray.array((a) -> a.object((o) -> o.stringValue("foo", "Foo")));
        }).build();

        System.out.println(lambdaBody.build());





    }
}
