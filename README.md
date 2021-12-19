# Маски в реальном времени для Хакатона "SDVentures Digital Nomad Hiring Weekend"
## Что было сделано?
Была написана библиотека, которая позволяет легко и быстро встроить в любое приложение поддержку масок в реальном времени.
## Что под капотом?
Google AR Core - библиотека для дополненной реальности от Google.

Она умеет сегментировать 468 точек и создавать поверхность лица.

![Google AR Core Face Landmarks](https://github.com/bumsun/masks_sdv_hack/blob/main/images/augmented-faces-468-point-face-mesh.png?raw=true)

Это позволяет на поверхность лица накладывать любые картинки или 3D модели.
![Google AR Core Augmented Faces](https://github.com/bumsun/masks_sdv_hack/blob/main/images/photo_fox.jpg?raw=true)

## Зачем нужна библиотека если гугл итак все сделал?
Гугл все сделал. Но для разработчиков, которые хотят получить быстрый результат, нужно потраить не мало времени на освоение API. Так же для многих не стандартных случаев в руководстве недостаточно примеров и объяснений. Данная библиотека восполнит этот пробел и поможет быстро настроить любую маску по ваши нужды.

## Как же пользоваться данной библиотекой?
+ На всякий случай можете проверить [список поддерживаемых устройств](https://developers.google.com/ar/devices#google_play) на наличие поддержки вашим устройством Google AR Core. 
+ [Скачайте apk файл](https://github.com/bumsun/masks_sdv_hack/blob/main/app/release/app-release.apk), чтобы посмотреть на возможности и качество масок. Если по какой-то причине приложение не запускается, то попробуйте установить [Google Play Services for AR](https://play.google.com/store/apps/details?id=com.google.ar.core&hl=en)
+ Если вас все устраивает, тогда склонируйте и запустите проект в [Android Studio](https://developer.android.com/studio).

## Api библиотеки

Всю работу библиотеки можно разбить на 2 части.  
1. Создание конфига:
```
//Этот код описывает конфиг для одной маски
String[] mask1part1 = {"models/3_deer_horns.png", MasksHelper.Positions.FOREHEAD.toString(),"2.4", "models/flat_square.obj"} ;
String[][] mask1 = {mask1part1};

List<String[][]> maskConfig = new ArrayList<>();
maskConfig.add(mask1);
MaskConfig.setInstance(maskConfig);
```
2. Запуск экрана с выводом изображения с фронтальной камеры и накладывание маски в реальном времени:
```
Intent intent = new Intent(this, AugmentedFacesActivity.class);
startActivity(intent);
```
В итоге на экране будет такая маска  
![Google AR Core Augmented Faces](https://github.com/bumsun/masks_sdv_hack/blob/main/images/photo_deer.jpg?raw=true)

## Более подробно про создание конфига

1. Для описание бесконечного количества масок используется тройная вложенность.
```
List<String[][]> maskConfig = new ArrayList<>();
```

2. Первая вложенность описывает первую часть первой маски. Например здесь это рога
```
String[] mask1part1 = {"models/3_deer_horns.png", MasksHelper.Positions.FOREHEAD.toString(),"2.4", "models/flat_square.obj"}
```

3. Название параметров
```
String[] mask1part1 = {"Пусть к иозображению (Обязательно)", "Позиционирование (Не обязательно, по дефолту 0)","Масштаб (Не обязательно, по дефолту 1)", "Пусть к 3D модели объекта (не обязательно)"}
```

4. Дальше из таких элементов формируется 1 более сложная маска.
```
String[][] mask1 = {mask1part1,mask1part2};

```

5. Затем различные маски добавляем в архив
```
maskConfig.add(mask1);
maskConfig.add(mask2);
```

6. Наконец, чтобы это все могло рисоваться в реальном времени, нужно сохранить конфиг и запустить экран с камерой.
```
MaskConfig.setInstance(maskConfig);
Intent intent = new Intent(this, AugmentedFacesActivity.class);
startActivity(intent);
```

## Теперь вы готовы увидеть конфиг из данного приложения

```
private void initMasksConfig() {
    String[] mask1part1 = {"models/3_deer_horns.png", MasksHelper.Positions.FOREHEAD.toString(),"2.4", "models/flat_square.obj"} ;
    String[][] mask1 = {mask1part1};

    String[] mask2part1 = {"models/cap_ducs.png",MasksHelper.Positions.FOREHEAD.toString(),"1", "models/cap3.obj"} ;
//        String[] mask2part2 = {"models/part_cap.png"};
    String[] mask2part2 = {"models/part_cap.png", MasksHelper.Positions.FOREHEAD.toString(),"2.4", "models/flat_square2.obj"};
    String[][] mask2 = {mask2part1,mask2part2};

    String[] mask2_1part1 = {"models/cap_duck5.png",MasksHelper.Positions.FOREHEAD.toString(),"2", "models/flat_square4.obj"} ;
//        String[] mask2part2 = {"models/part_cap.png"};
    String[][] mask2_1 = {mask2_1part1};

    String[] mask3part1 = {"models/nose_fox.png",MasksHelper.Positions.NOSE.toString(),"1", "models/nose_fox.obj"} ;
    String[] mask3part2 = {"models/ear_fox.png",MasksHelper.Positions.FOREHEAD_RIGHT.toString(),"1", "models/forehead_right.obj"} ;
    String[] mask3part3 = {"models/ear_fox.png",MasksHelper.Positions.FOREHEAD_LEFT.toString(),"1", "models/forehead_left.obj"} ;
    String[] mask3part4 = {"models/nose_fox2.png",MasksHelper.Positions.NOSE.toString(),"1", "models/nose_fox2.obj"} ;
    String[][] mask3 = {mask3part1,mask3part2,mask3part3,mask3part4};

    String[] mask4part1 = {"models/glass.png"};
    String[][] mask4 = {mask4part1};

    String[] mask5part1 = {"models/nose_bear.png",MasksHelper.Positions.NOSE.toString(),"1", "models/nose.obj"} ;
    String[] mask5part2 = {"models/ear_bear.png",MasksHelper.Positions.FOREHEAD_RIGHT.toString(),"1", "models/ear_bear_right.obj"} ;
    String[] mask5part3 = {"models/ear_bear.png",MasksHelper.Positions.FOREHEAD_LEFT.toString(),"1", "models/ear_bear_left.obj"} ;
    String[][] mask5 = {mask5part1,mask5part2,mask5part3};

    String[] mask6part1 = {"models/cap_green.png",MasksHelper.Positions.FOREHEAD.toString(),"2.1", "models/flat_square3.obj"} ;
    String[] mask6part2 = {"models/glass2.png"};
    String[][] mask6 = {mask6part1,mask6part2};

    List<String[][]> maskConfig = new ArrayList<>();

    maskConfig.add(mask1);
    maskConfig.add(mask2);
    maskConfig.add(mask2_1);

    maskConfig.add(mask3);
    maskConfig.add(mask4);
    maskConfig.add(mask5);
    maskConfig.add(mask6);
    MaskConfig.setInstance(maskConfig);
}
```

## Объяснение работы с ".obj" файлами
Это формат для хранения 3D объектов.
+ В идеале с ними работать в профессиональном пакете вроде [blender](https://www.blender.org).
Но если у вас слабый ноут или вы не хотите тратить лишние деньги на такие программы, тогда есть онлайн решение:
+ [threejs.org](https://threejs.org/editor) 
![Free 3D editor](https://github.com/bumsun/masks_sdv_hack/blob/main/images/3d_editor.jpeg?raw=true)
Рекомендую в этот редактор позагружать файлы из этого проекта, подвигать их по разным осям, помасштабировать и экспориторовать обратно в проект с заменой.
Так вы сможете довольно хорошо настроить свою 3D модель под ваши потребности.

+ Так же есть [такое](https://clara.io/) более функциональное, но более сложное для освоения онлайн решение.
![Free 3D editor](https://github.com/bumsun/masks_sdv_hack/blob/main/images/3d_editor2.jpeg?raw=true)









