package br.com.caelum.cadastro.service;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import br.com.caelum.cadastro.converter.AlunoConverter;

public class SendStudentsTask extends AsyncTask<String, Object, String> {
    private Context context;

    public SendStudentsTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... objects) {
        String json = objects[0];
        String mean = new WebClient().fetchMean(json);
        return mean;
    }

    @Override
    protected void onPostExecute(String mean) {
        super.onPostExecute(mean);

        Toast.makeText(context, mean, Toast.LENGTH_SHORT).show();
    }
}
