package br.com.caelum.cadastro.fragment;

import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import br.com.caelum.cadastro.CadastroApplication;
import br.com.caelum.cadastro.database.AlunoDao;
import br.com.caelum.cadastro.gps.Gps;
import br.com.caelum.cadastro.model.Aluno;

public class MapFragment extends SupportMapFragment {

    @Override
    public void onResume() {
        super.onResume();

        getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {

                Gps gps = new Gps(getContext(), googleMap);
                gps.initSearch();
                CadastroApplication app = (CadastroApplication) getActivity().getApplication();

                AlunoDao dao = app.getAlunoDao();

                List<Aluno> students = dao.getAll();

                Geocoder geoCoder = new Geocoder(getContext());
                for (Aluno student : students) {


                    try {
                        LatLng coord = getCoord(geoCoder, student);

                        if (coord != null) {


                            MarkerOptions maker = getMarker(student, coord);
                            googleMap.addMarker(maker);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private MarkerOptions getMarker(Aluno student, LatLng coord) {

        MarkerOptions maker = new MarkerOptions();

        maker.title(student.getName());
        maker.snippet(student.getEmail());
        maker.position(coord);

        return maker;
    }

    private LatLng getCoord(Geocoder geoCoder, Aluno student) throws IOException {

        List<Address> addresses = geoCoder.getFromLocationName(student.getAddress(), 1);

        if (!addresses.isEmpty()) {
            Address addr = addresses.get(0);

            return new LatLng(addr.getLatitude(), addr.getLongitude());
        }
        else return null;
    }
}
