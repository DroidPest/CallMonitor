package com.mk.networking.authentication.model

data class TokenBody(
    val grant_type: String = "client_credentials",
    val client_id: String = "J7xUav6nRpbEhbIKODmiDm4jY3DqQNxB0RNPTH9xSoQzYMA1cE",
    val client_secret: String = "jxhklnUIJBrItU5YU9sON7rCoNfNADMahkYNjm5o",
)
