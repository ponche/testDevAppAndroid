package domo.openclassroom.com.premiereactivite;

import android.app.Activity;
import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;

/**
 * Created by Remi on 30/01/2017.
 *
 * Activity de test pour explorer les fonctions da la camera android.
 * pas encore au point. Activity plante !!!!
 */

public class CameraActivity extends Activity
{
    //Attributs
    protected CameraManager m_gestionCamera;
    protected String[] m_listeCamera;

    //Methodes

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //Selection de la vue
        setContentView(R.layout.activity_camera);

        // Test camera
        m_gestionCamera = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            m_listeCamera = m_gestionCamera.getCameraIdList();
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

    }
}
