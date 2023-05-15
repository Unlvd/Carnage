package com.example.carnage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import android.widget.CheckBox
import com.example.carnage.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var attackCheckedBox: CheckBox? = null
    private var blockFirstCheckedBox: CheckBox? = null
    private var blockSecondCheckedBox: CheckBox? = null
    private val maxPlayerHp = 100
    private val maxEnemyHp = 100
    private val enemyAttack = 3..10
    private val playerAttack = 3..10


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var currentPlayerHp = maxPlayerHp
        var currentEnemyHp = maxEnemyHp
        var playerAttackPosition = 0


        selectCheckBox(binding.cbHead)
        selectCheckBox(binding.cbChest)
        selectCheckBox(binding.cbBelt)
        selectCheckBox(binding.cbLegs)
        selectBlockCheckBox(binding.cbBlockHead)
        selectBlockCheckBox(binding.cbBlockChest)
        selectBlockCheckBox(binding.cbBlockBelt)
        selectBlockCheckBox(binding.cbBlockLegs)
        binding.enemyHpLevel.text = "$currentEnemyHp / $maxEnemyHp"
        binding.playerHpLevel.text = "$currentPlayerHp / $maxPlayerHp"

        binding.attackButton.setOnClickListener{
            val enemyAttackFocus : Int = (1..4).random()
            val enemyAttackName : String = enemyAttackName(enemyAttackFocus)
            val enemyFirstBlock : Int = (1..4).random()
            var enemySecondBlock : Int = (1..4).random()
            while(enemyFirstBlock == enemySecondBlock){
                enemySecondBlock = (1..4).random()
            }
            val enemySecondBlockTrue = enemySecondBlock
            val playerAttackFocus =  playerAttackPosition(attackCheckedBox)
            val playerAttackName = enemyAttackName(playerAttackFocus)
            val playerBlockFirstPosition = playerBlockPosition(blockFirstCheckedBox)
            val playerBlockSecondPosition = playerBlockPosition(blockSecondCheckedBox)
            if (enemyAttackFocus == playerBlockFirstPosition || enemyAttackFocus == playerBlockSecondPosition){
                binding.topDialog.text = "Демон пытался ударить вас $enemyAttackName ,но вы заблокировали удар"
            } else {
                val damage = enemyAttack.random()
                currentPlayerHp -= damage
                binding.topDialog.text = "Демон ударил вас $enemyAttackName и нанес $damage урона $currentPlayerHp / $maxPlayerHp"
                binding.playerHpLevel.text = "$currentPlayerHp / $maxPlayerHp"
            }
            if(playerAttackFocus == enemyFirstBlock||playerAttackFocus == enemySecondBlock){
                binding.bottomDialog.text = "Вы пытался ударить демона $playerAttackName ,но он заблокировал удар"
            } else {
                val damage = playerAttack.random()
                currentEnemyHp -= damage
                binding.bottomDialog.text = "Вы ударили демона $playerAttackName и нанесли $damage урона $currentEnemyHp / $maxEnemyHp"
                binding.enemyHpLevel.text = "$currentEnemyHp / $maxEnemyHp"
            }

        }
    }




    private fun attackButtonVisibility(){
        if(attackCheckedBox?.isChecked == true&&blockFirstCheckedBox?.isChecked ==true&&blockSecondCheckedBox?.isChecked == true){
            binding.attackButton.visibility = View.VISIBLE
        }
    }

    private fun enemyAttackName(s: Int) : String{
        val a = when (s){
            1 -> "в Голову"
            2 -> "по Корпусу"
            3 -> "в Пояс"
            else -> "по Ногам"
        }
        return a
    }


    private fun playerAttackPosition(checkBox: CheckBox?) : Int {
        var i = 0
        if (checkBox == binding.cbHead) {
            i = 1
        }
             else if (checkBox == binding.cbChest ) {
                 i = 2
        }
             else if (checkBox == binding.cbBelt  ) {
                 i = 3
        }
             else if (checkBox == binding.cbLegs  ) {
                 i = 4
        }
        return i

    }

    private fun playerBlockPosition(checkBox: CheckBox?) : Int {
        var i = 0
        if (checkBox == binding.cbBlockHead) {
            i = 1
        }
        else if (checkBox == binding.cbBlockChest ) {
            i = 2
        }
        else if (checkBox == binding.cbBlockBelt  ) {
            i = 3
        }
        else if (checkBox == binding.cbBlockLegs  ) {
            i = 4
        }
        return i

    }

    private fun selectCheckBox(checkBox: CheckBox) {
        checkBox.setOnClickListener {
            attackCheckedBox?.isChecked = false
            attackCheckedBox = checkBox
            attackCheckedBox?.isChecked = true
            attackButtonVisibility()
        }
    }
    private fun selectBlockCheckBox(checkBox: CheckBox){
        checkBox.setOnClickListener {
            if(blockFirstCheckedBox?.isChecked !== true) {
                blockFirstCheckedBox = checkBox
                blockFirstCheckedBox?.isChecked = true
            } else if (blockSecondCheckedBox?.isChecked !== true) {
                blockSecondCheckedBox = checkBox
                blockSecondCheckedBox?.isChecked = true
            } else {
                blockFirstCheckedBox?.isChecked = false
                blockFirstCheckedBox = blockSecondCheckedBox
                blockSecondCheckedBox = checkBox
                blockSecondCheckedBox?.isChecked = true
            }
            attackButtonVisibility()



        }
    }
    
}