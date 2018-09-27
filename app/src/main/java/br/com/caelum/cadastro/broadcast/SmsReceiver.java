package br.com.caelum.cadastro.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.telephony.SmsMessage;

import br.com.caelum.cadastro.CadastroApplication;
import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.database.AlunoDao;

public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Object[] pdus = (Object[]) intent.getSerializableExtra("pdus");

        byte[] pdu = (byte[]) pdus[0];

        SmsMessage message = SmsMessage.createFromPdu(pdu);

        CadastroApplication app = (CadastroApplication) context.getApplicationContext();
        AlunoDao dao = app.getAlunoDao();

        if (dao.existsPhone(message.getDisplayOriginatingAddress()) > 0) {
            MediaPlayer mp = MediaPlayer.create(context, R.raw.msg);
            mp.start();
        }
    }
}
