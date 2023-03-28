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
 *  4. SQL injection
 *  5. Log injection (upgrade log4j version)
 *  6. CSRF (session attack)
 *           user
 *            |
 *          server
 *  7. CORS
 *
 *          domain1  -pre flight request>   domain2
 *                   <-   info
 *
 *                   - /xx + header:info
 *  8. DDOS
 *          a. block ip ranges / whitelist
 *          b. scale up
 *          c. shutdown services + display static pages
 *  9. password : hash password
 *  10. String / char[]
 *
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *
 *                                             save user info into security context(ThreadLocal)
 *                                                            |
 *                                                        jwt utility
 *                                                           |
 *                                                  retrieve header, get jwt token
 *                                                          |
 *   user <-> UsernamePasswordAuthenticationFilter <-> JWT filter <-> filter3 <-> spring mvc <-> controller (@PreAuthorize("hasAuthority('Admin')")
 *                  |
 *             get username password from post request
 *                 |
 *            attempt authenticate  ->  success -> trigger successful handler / controller(/login, post) -> generate jwt token -> return to user
 *                |
 *            DaoAuthenticationProvider
 *               |
 *        retrieve User from UserDetailsService interface
 *              |
 *         verify user info
 *
 */