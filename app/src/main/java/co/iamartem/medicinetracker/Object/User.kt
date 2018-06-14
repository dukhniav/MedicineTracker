package co.iamartem.medicinetracker.Object

/**
 * Created by Artem Dukhnitskiy on 6/13/18 !
 */
class User(private var password: Int) {
    fun getPassword() : Int{
        return password
    }

    fun setPassword(password: Int){
        this.password = password
    }
}