package net.idrok.muhandislik

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.Message
import android.os.Parcelable
import android.provider.MediaStore
import android.webkit.CookieManager
import android.webkit.PermissionRequest
import android.webkit.ValueCallback
import android.webkit.WebResourceRequest
import android.webkit.WebView
import androidx.activity.addCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import net.idrok.muhandislik.databinding.ActivityCasibomInfoBinding
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date

class WInfoActivity : AppCompatActivity() {
    private val wBinding by lazy {
        ActivityCasibomInfoBinding.inflate(layoutInflater)
    }
    private val wUrl by lazy {
        intent.getStringExtra(CASIBOM_URL).orEmpty()
    }
    private val wGoBack by lazy {
        intent.getBooleanExtra(CASIBOM_GO_BACK, false)
    }

    private var interestingWImageUri: Uri? = null
    private var interestingWMFilePathCallback: ValueCallback<Array<Uri>>? = null
    private val requestGalleryPermissionLauncherCross = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { _: Boolean ->
        askCrossCameraPermission()
    }

    private fun askCrossGalleryPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) ==
            PackageManager.PERMISSION_GRANTED
        ) {
            askCrossCameraPermission()
        }
        else {
            requestGalleryPermissionLauncherCross.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }

    private val requestCameraPermissionLauncherCross = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { _: Boolean ->
        interestingWTakePhoto()
    }


    private fun askCrossCameraPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.CAMERA
            ) ==
            PackageManager.PERMISSION_GRANTED
        ) {
            interestingWTakePhoto()
        }
        else {
            requestCameraPermissionLauncherCross.launch(android.Manifest.permission.CAMERA)
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun WebView.wSettings() {
        this.settings.apply {
            builtInZoomControls = true
            displayZoomControls = true
            loadWithOverviewMode = true
            useWideViewPort = true
            javaScriptEnabled = true
            domStorageEnabled = true
            setSupportZoom(false)
            allowFileAccess = true
            databaseEnabled = true
            allowContentAccess = true
        }
        this.webViewClient = object : android.webkit.WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                url?.let { view?.loadUrl(it) }
                return true
            }

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                val newUrl = request?.url.toString()
                view?.loadUrl(newUrl)
                return false
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                CookieManager.getInstance().flush()
            }
        }
        this.webChromeClient = object : android.webkit.WebChromeClient() {

            override fun onShowFileChooser(
                wWallpaper: WebView?,
                wGilePathCallback: ValueCallback<Array<Uri>>?,
                wGileChooserParams: FileChooserParams?
            ): Boolean {
                interestingWMFilePathCallback = null
                interestingWMFilePathCallback = wGilePathCallback
                askCrossGalleryPermission()
                return true
            }

            override fun onCreateWindow(
                interestingWView: WebView?,
                isWDialog: Boolean,
                isWUserGesture: Boolean,
                wResultMsg: Message?
            ): Boolean {
                val wResult = interestingWView?.hitTestResult ?: return false
                val wData = wResult.extra
                val wContext = interestingWView.context
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(wData))
                wContext.startActivity(browserIntent)
                return true
            }

            override fun onPermissionRequest(request: PermissionRequest?) {
                request?.grant(request.resources)
            }
        }
        this@WInfoActivity.onBackPressedDispatcher
            .addCallback(this@WInfoActivity) {
                if (this@wSettings.canGoBack()) {
                    this@wSettings.goBack()
                }
                else if (wGoBack) {
                    finish()
                }
            }
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(wBindle: Bundle?) {
        super.onCreate(wBindle)
        setContentView(wBinding.root)
        val wCookie = CookieManager.getInstance()
        wCookie.setAcceptCookie(true)
        wBinding.casibomInfo.wSettings()
        if (wBindle != null) {
            wBinding.casibomInfo.restoreState(wBindle)
        }
        else {
            wBinding.casibomInfo.loadUrl(wUrl)
        }
    }

    private val interestingWStartForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { wResult ->
        if (wResult.resultCode == RESULT_OK) {
            val uri = wResult.data?.data ?: interestingWImageUri
            ?: return@registerForActivityResult
            interestingWMFilePathCallback?.onReceiveValue(arrayOf(uri))
            interestingWMFilePathCallback = null
        }
        else if (wResult.resultCode == RESULT_CANCELED) {
            interestingWMFilePathCallback?.onReceiveValue(null)
        }
    }

    private fun interestingWTakePhoto() {
        val wPhotoFile: File?
        val wAuthorities: String = this.packageName + getString(R.string.wprovider)
        try {
            wPhotoFile = wCreateImageFile()
            interestingWImageUri = FileProvider
                .getUriForFile(this, wAuthorities, wPhotoFile)
        }
        catch (e: IOException) {
            e.printStackTrace()
        }
        val wCaptureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        wCaptureIntent.putExtra(MediaStore.EXTRA_OUTPUT, interestingWImageUri)
        val wPhoto = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        val wChooserIntent =
            Intent.createChooser(wPhoto, "Image Chooser")
        wChooserIntent.putExtra(
            Intent.EXTRA_INITIAL_INTENTS,
            arrayOf<Parcelable>(wCaptureIntent)
        )
        interestingWStartForResult.launch(wChooserIntent)
    }

    @SuppressLint("SimpleDateFormat")
    @Throws(IOException::class)
    private fun wCreateImageFile(): File {
        if (this.isDestroyed) return File("")
        val interestingWImageStorageDir = File(
            Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES
            ), getString(R.string.wandroidfolder)
        )
        if (!interestingWImageStorageDir.exists()) {
            interestingWImageStorageDir.mkdirs()
        }

        val wImageFileName =
            getString(R.string.wjpeg) + SimpleDateFormat(getString(R.string.wyyyymmdd_hhmmss)).format(
                Date()
            )
        return File(
            interestingWImageStorageDir,
            File.separator + wImageFileName + getString(R.string.wjpg)
        )
    }

    override fun onStop() {
        super.onStop()
        CookieManager.getInstance().flush()
        if (this.isDestroyed) {
            return
        }
    }


    companion object {
        @JvmStatic
        fun wIntent(
            wContext: Context,
            wUrl: String,
            wGoBack: Boolean
        ): Intent {
            if (wContext.isRestricted) {
                wContext.packageName
            }
            return Intent(wContext, WInfoActivity::class.java).apply {
                putExtra(CASIBOM_URL, wUrl)
                putExtra(CASIBOM_GO_BACK, wGoBack)
                if (!wGoBack) {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                }
            }
        }

        private const val CASIBOM_GO_BACK: String = "CASIBOM_BOOL_GO_BACK"
        private const val CASIBOM_URL = "CASIBOM_URL"
    }
}