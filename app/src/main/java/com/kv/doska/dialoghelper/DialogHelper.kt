package com.kv.doska.dialoghelper

import android.app.AlertDialog
import android.view.View
import com.kv.doska.MainActivity
import com.kv.doska.R
import com.kv.doska.accounthelper.AccountHelper
import com.kv.doska.databinding.SignDialogBinding

class DialogHelper(private val act: MainActivity) {
    private val accHelper = AccountHelper(act)

    fun createSignDialog(index: Int) {
        val builder = AlertDialog.Builder(act)
        val rootDialogElement = SignDialogBinding.inflate(act.layoutInflater)
        val view = rootDialogElement.root
        builder.setView(view)
        setDialogState(index, rootDialogElement)


        val dialog = builder.create()

        rootDialogElement.btSignUpIn.setOnClickListener {
            setOnClickSignUpIn(index, rootDialogElement, dialog)
        }
        dialog.show()
    }

    private fun setOnClickSignUpIn(
        index: Int,
        rootDialogElement: SignDialogBinding,
        dialog: AlertDialog?
    ) {
        dialog?.dismiss()
        if (index == DialogConst.SIGN_UP_STATE) {
            accHelper.signUpWithEmail(
                rootDialogElement.edSignEmail.text.toString(),
                rootDialogElement.edSignPassword.text.toString()
            )
        } else {
            accHelper.signInWithEmail(
                rootDialogElement.edSignEmail.text.toString(),
                rootDialogElement.edSignPassword.text.toString()
            )
        }
    }

    private fun setDialogState(index: Int, rootDialogElement: SignDialogBinding) {
        if (index == DialogConst.SIGN_UP_STATE) {
            rootDialogElement.tvSignTitle.text = act.resources.getString(R.string.ac_sign_up)
            rootDialogElement.btSignUpIn.text = act.resources.getString(R.string.sign_up_action)
        } else if (index == DialogConst.SIGN_IN_STATE) {
            rootDialogElement.tvSignTitle.text = act.resources.getString(R.string.ac_sign_in)
            rootDialogElement.btSignUpIn.text = act.resources.getString(R.string.sign_in_action)
            rootDialogElement.btforgetP.visibility = View.VISIBLE
        }
    }
}