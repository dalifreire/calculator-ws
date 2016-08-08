# calculator-ws
Simple calculator web service.

Implements a simple calculator web service that caches the results of its computations, with endpoints similar to the following:
-/add/{a}/{b}/{c}
-/subtract/{a}/{b}/{c}
-/multiply/{a}/{b}/{c}
-/divide/{a}/{b}

Each endpoint supports the GET method, and it returns the result in JSON format. If there is more than one call for the same operation on the same numbers, then the result is returned from the cache rather than being recomputed.

The calculator calls should support the addition, subtraction, or multiplication of up to three numbers in the same call.

The WADL is available at the following URL: http://localhost:9090/application.wadl
The rest services are available at the following URLs:
- http://localhost:9090/calculator/add/8.0/4/3.0
- http://localhost:9090/calculator/subtract/8.0/4/3.0
- http://localhost:9090/calculator/multiply/8.0/4/3.0
- http://localhost:9090/calculator/divide/8.0/4
