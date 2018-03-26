import org.springframework.cloud.contract.spec.Contract;


Contract.make {
    request{
        url "/check"
        method POST()
        body(
                age: value(regex('[2-9][0-9]'))
        )
        headers{
            contentType(applicationJson())
        }

    }

    response{
        status 200
        body("""
            {"status":  "OK"  }
"""
        )
        headers {
            contentType(applicationJson())
        }


    }


}