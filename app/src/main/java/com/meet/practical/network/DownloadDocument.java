package com.meet.practical.network;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.util.Log;
import android.widget.Toast;

import com.meet.practical.R;
import com.meet.practical.callback.BaseListener;
import com.meet.practical.util.AppUtils;
import com.meet.owlutilities.utils.AlertDialogHelper;
import com.meet.owlutilities.utils.NetworkHelper;
import com.meet.owlutilities.utils.ProgressDialogHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import okio.Okio;
import retrofit2.Response;

/**
 * Created by mine-pc on 03-10-2017.
 */

public class DownloadDocument<T> {

    private ProgressDialogHelper progressDialogHelper = null;

    public static final String ERROR_CONNECTION = "ERR0000";


    public void downloadFile(Context mContext, final BaseListener<T> listener, Observable<Response<ResponseBody>> observable, String message,String mainUrl, String outPath) {

        if (NetworkHelper.isConnected(mContext)) {
            if (message != null) {
                progressDialogHelper = new ProgressDialogHelper(mContext);
                progressDialogHelper.showCircularProgressDialog();
            }

            observable.flatMap(processResponse(mainUrl,outPath))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(handleResult(mContext, listener));


        } else {
            AlertDialogHelper.showDialog(mContext, "", mContext.getString(R.string.error_internet_connection), mContext.getString(R.string.ok), null, true, null, 0);
        }
    }

    public Function<Response<ResponseBody>, Observable<FileDownloadResponse>> processResponse(String mainUrl, String outPath) {
        return new Function<Response<ResponseBody>, Observable<FileDownloadResponse>>() {
            @Override
            public Observable<FileDownloadResponse> apply(@NonNull Response<ResponseBody> responseBodyResponse) throws Exception {
                return DownloadDocument.this.saveToDiskRx(responseBodyResponse, mainUrl, outPath);
            }
        };
    }


    public Observable<FileDownloadResponse> saveToDiskRx(final Response<ResponseBody> response, String MainUrl, String outPath) {
        return Observable.create(new ObservableOnSubscribe<FileDownloadResponse>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<FileDownloadResponse> subscriber) throws Exception {
                try {
                    FileDownloadResponse download = new FileDownloadResponse();
                    final File pdfFolder = new File(outPath);
                    if (!pdfFolder.exists()) {
                        pdfFolder.mkdirs();
                    }

                    String[] separated = MainUrl.split("/");
                    File outputFile = new File(outPath, separated[separated.length - 1]);

                    download.setFileName(outPath + "/" + separated[separated.length - 1]);
                    BufferedSink bufferedSink = Okio.buffer(Okio.sink(outputFile));
                    bufferedSink.writeAll(response.body().source());
                    bufferedSink.close();

                    subscriber.onNext(download);
                    subscriber.onComplete();
                } catch (IOException e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
        });
    }

    private Observer<FileDownloadResponse> handleResult(Context mContext, BaseListener<T> listener) {
        return new Observer<FileDownloadResponse>() {


            @Override
            public void onError(Throwable e) {
                if (progressDialogHelper != null) {
                    progressDialogHelper.hideCircularProgressDialog();
                }
                if (e != null) {
                    RetrofitException retrofitException = (RetrofitException) e;
                    String errorCode = "";
                    if (retrofitException != null) {
                        if (retrofitException.getKind() == RetrofitException.Kind.NETWORK) {
                            errorCode = ERROR_CONNECTION;
                        } else if (retrofitException.getKind() == RetrofitException.Kind.HTTP) {
                            try {
                                JSONObject jsonObject = new JSONObject(retrofitException.getResponse().errorBody().string());
                                String key = jsonObject.getJSONObject("message").keys().next();
                                errorCode = jsonObject.getJSONObject("message").getString(key);

                                if (retrofitException.getRetrofitExceptionBody() != null) {
                                    String errorMessage = AppUtils.getHttpErrorMessage(mContext, retrofitException.getRetrofitExceptionBody().getStatus());
                                    listener.onError(errorMessage, errorCode);
                                } else {
                                    listener.onError(retrofitException.getMessage(), errorCode);
                                }
                                return;
                            } catch (JSONException e1) {
                                Log.e("error1", e1.getMessage());
                            } catch (IOException e1) {
                                Log.e("error1", e1.getMessage());
                            }
                        } else {
                            Log.i(RXRetroManager.class.getName(), e.getMessage());
                        }

                        if (errorCode.equals(ERROR_CONNECTION)) {
                            listener.onError(retrofitException.getMessage(), errorCode);
                            return;
                        }
                    }
                }

            }

            @Override
            public void onComplete() {
                if (progressDialogHelper != null) {
                    progressDialogHelper.hideCircularProgressDialog();
                }
            }

            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(FileDownloadResponse fileDownloadResponse) {
                if (fileDownloadResponse != null) {
                   /* if (mContext instanceof PdfViewerActivity) {
                        // Toast.makeText(mContext, fileDownloadResponse.getFileName(), Toast.LENGTH_SHORT).show();
                        ((PdfViewerActivity) mContext).pdfDowmoloadComplete(fileDownloadResponse);
                    }*/
                    if (listener != null) {
                        listener.onSuccess((T) fileDownloadResponse);
                    }
                    Toast.makeText(mContext, fileDownloadResponse.getFileName(), Toast.LENGTH_SHORT).show();
                    MediaScannerConnection.scanFile(mContext, new String[]{fileDownloadResponse.getFileName()}, null, null);
                } else {
                    Toast.makeText(mContext, mContext.getString(R.string.error_file_download), Toast.LENGTH_SHORT).show();
                }
            }
        };
    }


}
