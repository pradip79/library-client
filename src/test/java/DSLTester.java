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

        LambdaDslJsonBody lambdaBody = newJsonBody((root)-> {
            root.minArrayLike("Actors", 2, (actor)->{
                actor.stringType("name", "Tom Cruise");
                actor.numberType("age", 56);
                actor.date("birthDate", "dd MMMM yyyy");
                actor.stringType("photo", "https://jsonformatter.org/img/tom-cruise.jpg");
                actor.array("children", (child)->{
                    child.stringType("Suri");
                    child.stringType("Isabella");
                });
            });
        });


        System.out.println(lambdaBody.build());


        body = new PactDslJsonArray()
                .arrayEachLike(3)
                    .integerType("isbn")
                .closeObject();
        System.out.println(body);


    }
}
