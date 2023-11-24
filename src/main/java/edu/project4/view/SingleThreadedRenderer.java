package edu.project4.view;

import edu.project4.model.FractalImage;
import edu.project4.model.Pixel;
import edu.project4.model.Point;
import edu.project4.model.Rectangle;
import edu.project4.transformation.Transformation;
import edu.project4.util.RandomUtils;
import java.util.List;
import java.util.Random;

public class SingleThreadedRenderer implements Renderer {
    @Override
    public FractalImage render(
        FractalImage canvas,
        Rectangle world,
        List<Transformation> variations,
        int samples,
        short iterPerSample,
        long seed
    ) {
        for (int num = 0; num < samples; ++num) {
            final int symmetry = 10; // ??

            Point pw = RandomUtils.nextPointInRectangle(world);

            for (short step = 0; step < iterPerSample; ++step) {
                Transformation transformation = RandomUtils.nextTransformation(variations);

                pw = transformation.apply(pw);

                double theta2 = 0.0;
                for (int s = 0; s < symmetry; theta2 += Math.PI * 2 / symmetry, ++s) {
                    Point pwr = rotate(pw, theta2);
                    if (!world.contains(pwr)) {
                        continue;
                    }

//                    Pixel pixel = mapRange(world, pwr, canvas);
//                    Pixel pixel = canvas.pixel(pwr.x(), pwr.y());
//                    if (pixel == null) {
//                        continue;
//                    }

                    // 1. делаем лок на время работы с пикселем
                    // 2. подмешиваем цвет и увеличиваем hit count
                }
            }
        }
        return canvas;
    }

    Point rotate(Point point, double theta) {
        return new Point(
            point.x() * Math.cos(theta) + point.y() * Math.sin(theta),
            -point.x() * Math.sin(theta) + point.y() * Math.cos(theta)
        );
    }

//    void r(int n, int eqCount, int it, int xRes, int yRes) {
////        Генерируем eqCount аффинных преобразований со стартовыми цветами;
//        Random random = new Random();
//        int XMIN = -1;
//        int XMAX = 1;
//        int YMIN = -1;
//        int YMAX = 1;
//
//        for (int num = 0; num < n; num++) {
//            //Для изображения размером 1920х1080 можно
//            //взять XMIN=-1.777,XMAX=1.777,YMIN=-1,YMAX=1
//            //В этом случае в большинстве нелинейных преобразований с боков не будет оставаться черных областей
//            double newX = random.nextDouble(-1, 1);
//            double newY = random.nextDouble(-1, 1);
//            //Первые 20 итераций точку не рисуем, т.к. сначала надо найти начальную
//            for (int i = 0; i < 20; i++) {
//                newX = coeff[i].a * newX + coeff[i].b * newY + coeff[i].c;
//                newY = coeff[i].d * newX + coeff[i].e * newY + coeff[i].f;
//            }
//
//            for (int step = 0; step < it; step++) {
//                //Выбираем одно из аффинных преобразований
//                int i = random.nextInt(0, eqCount);
//                //и применяем его
//                newX = coeff[i].a * newX + coeff[i].b * newY + coeff[i].c;
//                newY = coeff[i].d * newX + coeff[i].e * newY + coeff[i].f;
////                Применяем нелинейное преобразование;
//                if (newX >= XMIN && newX <= XMAX && newY >= YMIN && newY <= YMAX) {
//                    //Вычисляем координаты точки, а затем задаем цвет
//                    double x1 = xRes - Trunc(((XMAX - newX) / (XMAX - XMIN)) * xRes);
//                    double y1 = yRes - Trunc(((YMAX - newY) / (YMAX - YMIN)) * yRes);
//                    //Если точка попала в область изображения
//                    if (x1 < xRes && y1 < yRes) {
//                        //то проверяем, первый ли раз попали в нее
//                        if (pixels[x1][y1].counter == 0) {
//                            //Попали в первый раз, берем стартовый цвет у соответствующего аффинного преобразования
//                            pixels[x1][y1].red = coeff[i].red;
//                            pixels[x1][y1].green = coeff[i].green;
//                            pixels[x1][y1].blue = coeffs[i].blue;
//                        } else {
//                            //Попали не в первый раз, считаем так:
//                            pixels[x1][y1].red = (pixels[x1][y1].red + coeff[i].red) / 2;
//                            pixels[x1][y1].green = (pixels[x1][y1].green + coeff[i].green) / 2;
//                            pixels[x1][y1].blue = (pixels[x1][y1].blue + coeff[i].blue) / 2;
//                        }
//                        //Увеличиваем счетчик точки на единицу
//                        pixels[x1][y1].counter++;
//                    }
//                }
//            }
//        }
//    }
}
