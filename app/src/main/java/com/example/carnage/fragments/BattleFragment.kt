package com.example.carnage.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import com.example.carnage.R
import com.example.carnage.databinding.FragmentBattleBinding
import com.example.carnage.fragments.contract.router


class BattleFragment : Fragment() {
    private var attackCheckedBox: CheckBox? = null
    private var blockFirstCheckedBox: CheckBox? = null
    private var blockSecondCheckedBox: CheckBox? = null
    private val maxPlayerHp = 100
    private val maxEnemyHp = 100
    private val enemyAttack = 3..10
    private val playerAttack = 3..10
    private var _binding: FragmentBattleBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBattleBinding.inflate(inflater,container,false)

        var currentPlayerHp = maxPlayerHp
        var currentEnemyHp = maxEnemyHp

        binding.playerImage.setImageResource(R.drawable._271514449)
        binding.enemyImage.setImageResource(R.drawable.red_devil)

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
                if(currentPlayerHp <=0){
                    router().replaceFragment(LoseFragment())
                }
            }
            if(playerAttackFocus == enemyFirstBlock||playerAttackFocus == enemySecondBlock){
                binding.bottomDialog.text = "Вы пытался ударить демона $playerAttackName ,но он заблокировал удар"
            } else {
                val damage = playerAttack.random()
                currentEnemyHp -= damage
                binding.bottomDialog.text = "Вы ударили демона $playerAttackName и нанесли $damage урона $currentEnemyHp / $maxEnemyHp"
                binding.enemyHpLevel.text = "$currentEnemyHp / $maxEnemyHp"
                if(currentEnemyHp <=0){
                    router().replaceFragment(WinFragment())
                }
            }

        }

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

    companion object {
        @JvmStatic
        fun newInstance() =
            BattleFragment().apply {}
    }

    private fun attackButtonVisibility(){
        if(attackCheckedBox?.isChecked == true&&blockFirstCheckedBox?.isChecked ==true&&blockSecondCheckedBox?.isChecked == true){
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
        val position = when (checkBox){
            binding.cbHead -> 1
            binding.cbChest -> 2
            binding.cbBelt -> 3
            else -> 4
        }
        return position
    }

    private fun playerBlockPosition(checkBox: CheckBox?) : Int {
        val position = when (checkBox){
            binding.cbBlockHead -> 1
            binding.cbBlockChest -> 2
            binding.cbBlockBelt -> 3
            else -> 4
        }
        return position
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
