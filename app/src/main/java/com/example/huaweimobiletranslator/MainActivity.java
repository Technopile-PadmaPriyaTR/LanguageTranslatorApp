package com.example.huaweimobiletranslator;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.mlsdk.common.MLException;
import com.huawei.hms.mlsdk.langdetect.MLLangDetectorFactory;
import com.huawei.hms.mlsdk.langdetect.local.MLLocalLangDetector;
import com.huawei.hms.mlsdk.langdetect.local.MLLocalLangDetectorSetting;
import com.huawei.hms.mlsdk.model.download.MLLocalModelManager;
import com.huawei.hms.mlsdk.model.download.MLModelDownloadStrategy;
import com.huawei.hms.mlsdk.translate.MLTranslatorFactory;
import com.huawei.hms.mlsdk.translate.cloud.MLRemoteTranslateSetting;
import com.huawei.hms.mlsdk.translate.cloud.MLRemoteTranslator;
import com.huawei.hms.mlsdk.translate.local.MLLocalTranslateSetting;
import com.huawei.hms.mlsdk.translate.local.MLLocalTranslator;
import com.huawei.hms.mlsdk.translate.local.MLLocalTranslatorModel;

public class MainActivity extends AppCompatActivity {
    Spinner itemSpinner;
    MLLocalLangDetector mlLocalLangDetector;
    Button translateButton;
    TextView detectedLangText;
    TextView translatedText;
    EditText sourceText;
    String lang,code,dlang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        translateButton = findViewById(R.id.translateButton);
        detectedLangText = findViewById(R.id.detectedText);
        translatedText = findViewById(R.id.translatedText);
        sourceText = findViewById(R.id.sourceText);
        itemSpinner= findViewById(R.id.itemsspinner);

        MLLangDetectorFactory factory = MLLangDetectorFactory.getInstance();
        MLLocalLangDetectorSetting setting = new MLLocalLangDetectorSetting.Factory()

                .setTrustedThreshold(0.01f)
                .create();
        mlLocalLangDetector = factory.getLocalLangDetector(setting);

        translateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = sourceText.getText().toString();
                detectLanguage(text);
            }
        });


    }

    public void detectLanguage(final String source_Text) {
        Task<String> firstBestDetectTask = mlLocalLangDetector.firstBestDetect(source_Text);
        firstBestDetectTask.addOnSuccessListener(new OnSuccessListener<String>() {
            @Override
            public void onSuccess(String s) {
                switch (s){
                    case "ar":
                        dlang="Arabic";
                        break;
                    case "da":
                        dlang="Danish";
                        break;
                    case "de":
                        dlang="German";
                        break;
                    case "en":
                        dlang="English";
                        break;
                    case "es":
                        dlang="Spanish";
                        break;
                    case "fi":
                        dlang="Finnish";
                        break;
                    case "fr":
                        dlang="French";
                        break;
                    case "it":
                        dlang="Italian";
                        break;
                    case "ja":
                        dlang="Japanese";
                        break;
                    case "ko":
                        dlang="Korean";
                        break;
                    case "pl":
                        dlang="Polish";
                        break;
                    case "pt":
                        dlang="Portuguese";
                        break;
                    case "ru":
                        dlang="Russian";
                        break;
                    case "sv":
                        dlang="Swedish";
                        break;
                    case "th":
                        dlang="Thai";
                        break;
                    case "tr":
                        dlang="Turkish";
                        break;
                    case "zh":
                        dlang="Chinese";
                        break;
                    case "ms":
                        dlang="Malay";
                        break;
                    case "no":
                        dlang="Norwegian";
                        break;
                    case "vi":
                        dlang="Vietnamese";
                        break;
                    case "id":
                        dlang="Indonesian";
                        break;
                    case "cs":
                        dlang="Czech";
                        break;
                    case "he":
                        dlang="Hebrew";
                        break;
                    case "el":
                        dlang="Greek";
                        break;
                    case "hi":
                        dlang="Hindi";
                        break;
                    case "tl":
                        dlang="Tagalog";
                        break;
                    case "sr":
                        dlang="Serbian";
                        break;
                    case "ro":
                        dlang="Romanian";
                        break;
                    case "zh-HK":
                        dlang="Traditional Chinese";
                        break;
                    case "my":
                        dlang="Burmese";
                        break;
                    case "ta":
                        dlang="Tamil";
                        break;
                    case "hu":
                        dlang="Hungarian";
                        break;
                    case "nl":
                        dlang="Dutch";
                        break;
                    case "fa":
                        dlang="Persian";
                        break;
                    case "sk":
                        dlang="Slovak";
                        break;
                    case "et":
                        dlang="Estonian";
                        break;
                    case "lv":
                        dlang="Latvian";
                        break;
                    case "km":
                        dlang="Central Khmer";
                        break;
                    default:
                        Toast.makeText(MainActivity.this, "Could not identify the language", Toast.LENGTH_SHORT).show();
                        break;
                }
                detectedLangText.setText(dlang);
                translate(source_Text,s);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Toast.makeText(MainActivity.this, "Could not identify the language", Toast.LENGTH_SHORT).show();

            }
        });
        if (mlLocalLangDetector != null) {
            mlLocalLangDetector.stop();
        }


    }
    public void translate(String sourceText, String sourceLangCode ){
        lang = itemSpinner.getSelectedItem().toString();
        switch (lang){

            case "Arabic":
                code="ar";
                break;
            case "Danish":
                code="da";
                break;
            case "German":
                code="de";
                break;
            case "English":
                code="en";
                break;
            case "Spanish":
                code="es";
                break;
            case "Finnish":
                code="fi";
                break;
            case "French":
                code="fr";
                break;
            case "Italian":
                code="it";
                break;
            case "Japanese":
                code="ja";
                break;
            case "Korean":
                code="ko";
                break;
            case "Polish":
                code="pl";
                break;
            case "Portuguese":
                code="pt";
                break;
            case "Russian":
                code="ru";
                break;
            case "Swedish":
                code="sv";
                break;
            case "Thai":
                code="th";
                break;
            case "Turkish":
                code="tr";
                break;
            case "Chinese":
                code="zh";
                break;
            case "Malay":
                code="ms";
                break;
            case "Norwegian":
                code="no";
                break;
            case "Vietnamese":
                code="vi";
                break;
            case "Indonesian":
                code="id";
                break;
            case "Czech":
                code="cs";
                break;
            case "Hebrew":
                code="he";
                break;
            case "Greek":
                code="el";
                break;
            case "Hindi":
                code="hi";
                break;
            case "Tagalog":
                code="tl";
                break;
            case "Serbian":
                code="sr";
                break;
            case "Romanian":
                code="ro";
                break;
            case "Traditional Chinese":
                code="zh-HK";
                break;
            case "Burmese":
                code="my";
                break;
            case "Tamil":
                code="ta";
                break;
            case "Hungarian":
                code="hu";
                break;
            case "Dutch":
                code="nl";
                break;
            case "Persian":
                code="fa";
                break;
            case "Slovak":
                code="sk";
                break;
            case "Estonian":
                code="et";
                break;
            case "Latvian":
                code="lv";
                break;
            case "Central Khmer":
                code="km";
                break;
            case "Select Language to Translate":
                Toast.makeText(MainActivity.this, "Select a valid language", Toast.LENGTH_SHORT).show();
                break;
        }
        MLRemoteTranslateSetting setting = new MLRemoteTranslateSetting
                .Factory()
                .setSourceLangCode(sourceLangCode)
                .setTargetLangCode(code)
                .create();
        MLRemoteTranslator mlRemoteTranslator = MLTranslatorFactory.getInstance().getRemoteTranslator(setting);

        final Task<String> task = mlRemoteTranslator .asyncTranslate(sourceText);
        task.addOnSuccessListener(new OnSuccessListener<String>() {
            @Override
            public void onSuccess(String text) {

                translatedText.setText(text);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                try {
                    MLException mlException = (MLException)e;
                    int errorCode = mlException.getErrCode();
                    String errorMessage = mlException.getMessage();
                } catch (Exception error) {

                }
            }
        });
        if (mlRemoteTranslator!= null) {
            mlRemoteTranslator.stop();
        }

    }



}