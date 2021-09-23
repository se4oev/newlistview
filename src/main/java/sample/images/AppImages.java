package sample.images;

import javafx.scene.image.Image;
import sample.entity.TestITestResult;
import sample.common.PathologyStatus;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by fomin on 25.03.2019.
 * Description:
 */
public class AppImages extends Images {

    private static final String imagesBasePath = '/' + AppImages.class.getPackage().getName().replace('.', '/') + '/';

    public static Image createImage(String path) {
        return createImage(path, false);
    }
    
    public static Image createImage(String path, boolean isNullable) {
        if (path.charAt(0) != '/')
            path = imagesBasePath + path;
        if (isNullable && Images.class.getResource(path) == null)
            return null;
        return Images.createImage(path);
    }

//    public static Image getSmallLabDevice(LabDevice device) {
//        return createImage("lab_devices/small/" + device.getImage());
//    }
//
//    public static Image getSmallLabDevice(String image) {
//        return createImage("lab_devices/small/" + image);
//    }
//
//    public static Image getBigLabDevice(LabDevice device) {
//        return createImage("lab_devices/big/" + device.getImage());
//    }
//
    public static Image createPathologyImage(PathologyStatus status) {
        return createPathologyImage(status.getStatus());
    }

    public static Image createPathologyImage(int pathologyStatus) {
        if (pathologyStatus == 0)
            return null;
        String fileName = "pathology_" + pathologyStatus;
        if (pathologyStatus == TestITestResult.Pathology.PATHOLOGY)
            fileName = "pathology";
        return createImage("status/" + fileName + ".png");
    }

    public static Image getValidationImage(int validationStatus) {
        return createImage("status/validation_" + validationStatus + ".png");
    }

    public static Image getSmallLabDep(String imageName) {
        return createImage("lab_dep/small/" + imageName);
    }

    public static Image getBigLabDep(String imageName) {
        return createImage("lab_dep/big/" + imageName);
    }

//    public static Image getSmallContainerImage(Container.Type type, String image) {
//        return createImage("container/small/" + type.name() + "/" + image);
//    }
//
//    public static Image getSmallContainerImage(Container container) {
//        if (container == null)
//            return null;
//        return getSmallContainerImage(container.getType(), container.getImage());
//    }
//
//    public static Image getSmallContainerLabDepOverlay(Container container, LabDep labDep) {
//        if (container == null || labDep == null)
//            return null;
//        return createImage("container/small/" + container.getType().name() + "/lab_dep_overlay/" + labDep.getImage());
//    }
//
//    public static Image getBigContainerImage(SampleItem sample) {
//        return getBigContainerImage(sample.getContainerType(), sample.getContainerImage());
//    }
//
//    public static Image getBigContainerImage(Container container) {
//        if (container == null)
//            return null;
//        return createImage("container/big/" + container.getType().name() + "/" + container.getImage());
//    }
//
//    public static Image getBigContainerImage(Container.Type type, String image) {
//        return createImage("container/big/" + type.name() + "/" + image);
//    }
//
//    public static Image getBigContainerLabDepOverlay(Container container, LabDep labDep) {
//        if (container == null)
//            return null;
//        Container.Type type = container.getType();
//        return getBigContainerLabDepOverlay(type, labDep);
//    }
//
//    public static Image getBigContainerLabDepOverlay(Container.Type type, LabDep labDep) {
//        if (labDep == null || StringUtils.isNullOrEmpty(labDep.getImage()))
//            return null;
//        return createImage("container/big/" + type.name() + "/lab_dep_overlay/" + labDep.getImage());
//    }

    public static List<String> getAllLabDepImageNames() {
//        String path = imagesBasePath + "lab_dep/small";
//        return getAllImageNames(path);
        String[] names = {
                "blue.png",
                "green.png",
                "light_blue.png",
                "light_red.png",
                "orange.png",
                "pink.png",
                "red.png"
        };
        return Arrays.stream(names)
                     .sorted(String::compareTo)
                     .collect(Collectors.toList());
    }

//    public static List<String> getAllContainerImageNames(Container.Type type) {
////        String path = imagesBasePath + "container/small/" + type.name();
////        return getAllImageNames(path);
//
//        if (type == Container.Type.GLASS)
//            return Collections.singletonList("undefined.png");
//
//        String[] names = {
//                "undefined.png",
//                "black.png",
//                "blue.png",
//                "brown.png",
//                "green.png",
//                "grey.png",
//                "orange.png",
//                "red.png",
//                "violet.png",
//                "white.png",
//                "yellow.png"
//        };
//        return Arrays.stream(names)
//                     .sorted()
//                     .collect(Collectors.toList());
//    }
//
//    public static Image getStatusImage(SampleStatus status) {
//        if (status.getImage().isEmpty())
//            return null;
//        return createImage("status/" + status.getImage());
//    }
//
//    public static Image getStatusImage(IfaWorklistStatus status) {
//        if (status.getImage().isEmpty())
//            return null;
//        return createImage("status/" + status.getImage());
//    }
//
//    public static Image getStatusImage(MbioResearchStatus status) {
//        if (status.getImage().isEmpty())
//            return null;
//        return createImage("status/" + status.getImage());
//    }
//
//    public static Image createRefImage(Ref ref) {
//        return createImage("ref/" + ref.getImage(), true);
//    }

    public static Image getDliImportStatus(int importStatus) {
        if (importStatus == 1)
            return createImage("dli/importStatus.png");
        return null;
    }

    public static Image getDliExportStatus(int exportStatus) {
        if (exportStatus > 0)
            return createImage("dli/exportStatus" + exportStatus + ".png");
        return null;
    }

    public static List<Image> getAppImages() {
        return Arrays.asList(
                createImage("app/app_icon.png")
        );
    }

    public static List<String> getAllDeviceImageNames() {
//        String path = imagesBasePath + "lab_devices/small";
//        return getAllImageNames(path);
        String[] names = {
                "undefined.png",
                "auto_clinic.png",
                "semi_auto.png"
        };
        return Arrays.asList(names);
    }

    private static List<String> getAllImageNames(String path) {
        File dir = getResourceDir(path);
        File[] files = Objects.requireNonNull(dir.listFiles(), "Images are missing: " + path);
        List<String> list = Arrays.stream(files)
                                  .map(File::getName)
                                  .sorted(String::compareTo)
                                  .collect(Collectors.toList());
        String undefinedName = "undefined.png";
        if (list.contains(undefinedName)) {
            list.remove(undefinedName);
            list.add(0, undefinedName);
        }
        return list;
    }

    private static File getResourceDir(String path) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL url = loader.getResource(path.substring(1));
        return new File(url.getPath());

//        System.out.println("getResourceDir: " + path);
//        URI uri = AppResources.getResourceUri(path);
//        System.out.println("uri: " + uri);
//        return new File(uri);
    }

    public static Image getSelectLabDepImage() {
        return createImage("lab_dep/select_lab_dep.png");
    }

}
