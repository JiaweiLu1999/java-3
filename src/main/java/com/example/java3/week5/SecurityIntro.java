package com.example.java3.week5;



/**
 *  Symmetric key  : same key encrypt, same key decrypt
 *  Asymmetric key : public key encrypt,  private key decrypt
 *                   private key encrypt, public key decrypt
 *
 *
 *  1. authentication
 *      username, password
 *      2 factors verification
 *      email, phone
 *  2. authorization
 *      role based verification
 *
 *      OAuth2.0
 *
 *               user
 *                |
 *              [server  -> security server] same company
 *
 *              user  -> 1. google username password  ->  [3rd party security center]
 *                    <- 2. get access_code
 *               ||3. access_code
 *             [server]   -> 4. use your access_code + server info / id      ->    [3rd party security center]
 *                        <- 5. get token
 *
 *
 *      JWT: json web token
 *
 *          header.payload.signature
 *          header(algorithm)
 *          payload(data / user_id / expiration time / role info)
 *          signature = encryption(header.payload)
 *
 *
 *          encode[header.payload.encryption(header.payload)]
 *  3. HTTPS = Http + SSL / TLS
 *                      Certificate Authority
 *                  /                           \
 *             client          -> hi           server
 *                    <- hi(certification, public key)
 *          exchange public key + private key => symmetric key
 *                    ->  public key [generate random string] ->
 *                    <-  hash(same string)
 *                          generate symmetric key
 *                     -> symmetric key [data] ->
 *                     <- symmetric key [data] <-
 *
 *
 *
 *    Tomorrow :
 *      1. Spring security
 *      2. Microservice introduction
 */