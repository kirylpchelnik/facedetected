package pet.pechenka;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import org.opencv.highgui.HighGui;


public class Test {
        public static void main(String[] args)
        {
            try {
                // Загрузка библиотеки OpenCV
                System.load("D:\\FaceDetected\\src\\main\\resources\\opencv_java470.dll");

                // Загрузка изображения
                String imagePath = "D:\\FaceDetected\\src\\main\\resources\\image\\image1.jpg";
                Mat image = Imgcodecs.imread(imagePath);


                // Уменьшение размера изображения в два раза
                Mat resizedImage = new Mat();
                Imgproc.resize(image, resizedImage, new Size(image.cols() / 2, image.rows() / 2));

                // Вывод изображения после изменения размера
                HighGui.imshow("Resized Image", resizedImage);
                HighGui.waitKey(0);

                // Создание каскадного классификатора для обнаружения лиц
                CascadeClassifier faceCascade = new CascadeClassifier();
                String cascadePath = "D:\\FaceDetectionApp\\facepechenka\\src\\main\\resources\\haarcascade_frontalface_default.xml";
                faceCascade.load(cascadePath);

                // Преобразование изображения в оттенки серого для улучшения обнаружения лиц
                Mat grayImage = new Mat();
                Imgproc.cvtColor(resizedImage, grayImage, Imgproc.COLOR_BGR2GRAY);
                Imgproc.equalizeHist(grayImage, grayImage);

                // Вывод изображения после преобразования в оттенки серого
                HighGui.imshow("Gray Image", grayImage);
                HighGui.waitKey(0);

                // Обнаружение лиц на изображении
                MatOfRect faces = new MatOfRect();
                faceCascade.detectMultiScale(grayImage, faces);

                // Отрисовка прямоугольников вокруг обнаруженных лиц
                for (Rect rect : faces.toArray()) {
                    Imgproc.rectangle(resizedImage, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                            new Scalar(0, 255, 0), 2);
                }

                // Вывод изображения с прямоугольниками вокруг лиц
                HighGui.imshow("Face Detection", resizedImage);
                HighGui.waitKey(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}

