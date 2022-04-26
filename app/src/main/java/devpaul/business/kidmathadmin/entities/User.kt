package devpaul.business.kidmathadmin.entities

class User(
    var name: String? = null,
    var lastname: String? = null,
    var rol: String? = null,
    val email: String? = null,
    val password: String? = null)