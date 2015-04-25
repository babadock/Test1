package app.test1;

import java.io.File;

import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

import android.os.Environment;
import android.util.Log;

public class Test1Processor {
	private static final String TAG = "From Test1Processor";
	private static final boolean DEBUG = false;

	public Test1Processor() {
		// TODO Auto-generated constructor stub

	}

	public synchronized void sayHello() {
		Log.i(TAG, "Hello world");
	}

	public int main1(int argc, String argv[]) {
		if (argc < 3) {
			Log.i(TAG, "Not enough parameters");
			return -1;
		}
		Mat I;
		if (argc == 4 && !argv[3].equals("G")) {
			I = loadImageFromFile(argv[0]);
		} else
			// I = Highgui.imread(argv[0], Highgui.CV_LOAD_IMAGE_COLOR);
			I = loadImageFromFile(argv[0]);
		/*
		 * if (false !I.dataAddr() ) { Log.i(TAG, "The image" + argv[0] +
		 * " could not be loaded."); return -1; }
		 */
		int divideWith = 0;
		divideWith = Integer.parseInt(argv[1]);
		if (divideWith == 0) {
			// cout << "Invalid number entered for dividing. " << endl;
			Log.i(TAG, "Invalid number entered for dividing. ");
			return -1;
		}
		int[] table = new int[256];
		for (int i = 0; i < 256; ++i)
			table[i] = unsignedToBytes((byte) (divideWith * (i / divideWith)));
		// ------------------------------------------
		final int times = 100;
		double t = 0;
		// t = (double)getTickCount();
		for (int i = 0; i < times; ++i) {
			// Mat clone_i = I.clone();
			// J = ScanImageAndReduceC(clone_i, table);
		}

		// t = 1000*((double)getTickCount() - t)/getTickFrequency();
		// t /= times;

		/*
		 * cout << "Time of reducing with the C operator [] (averaged for " <<
		 * times << " runs): " << t << " milliseconds."<< endl;
		 */
		Log.i(TAG, "Time of reducing with the C operator [] (averaged for "
				+ times + " runs): " + t + " milliseconds.");
		return 0;
	}

	/*
	 * static Mat ScanImageAndReduceC(Mat I, byte[] table) { // accept only char
	 * type matrices //CV_Assert(I.depth() != sizeof(char)); int channels =
	 * I.channels(); int nRows = I.rows(); int nCols = I.cols() * channels; if
	 * (I.isContinuous()) { nCols *= nRows; nRows = 1; } int i,j; char[] p; for(
	 * i = 0; i < nRows; ++i) { p = I.ptr<byte>(i); for ( j = 0; j < nCols; ++j)
	 * { p[j] = table[p[j]]; } } return I; }
	 */
	public static int unsignedToBytes(byte b) {
		return b & 0xFF;
	}

	public Mat loadImageFromFile(String fileName) {
		Mat rgbLoadedImage = null;
		File root = Environment.getExternalStorageDirectory();
		File file = new File(root, fileName);
		// this should be in BGR format according to the
		// documentation.
		Mat image = Highgui.imread(file.getAbsolutePath());
		if (image.width() > 0) {
			rgbLoadedImage = new Mat(image.size(), image.type());
			Imgproc.cvtColor(image, rgbLoadedImage, Imgproc.COLOR_BGR2RGB);
			if (DEBUG)
				Log.d(TAG, "loadedImage: " + "chans: " + image.channels()
						+ ", (" + image.width() + ", " + image.height() + ")");
			image.release();
			image = null;
		}
		return rgbLoadedImage;
	}

}
