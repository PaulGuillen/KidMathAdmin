package devpaul.business.kidmathadmin.entities

class User(
    var userId: String? = null,
    var name: String? = null,
    var lastname: String? = null,
    var rol: String? = null,
    var email: String? = null,
    val password: String? = null)