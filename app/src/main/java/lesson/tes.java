package lesson;
class Battery7v{
    // 製造者やモデルなどのインスタンス変数
    public String manufacturer;
    public String model;
    
    // クラス変数。すべてのBattery7vオブジェクトが共有
    public static double voltage = 7.2;
    public static String type = "Lithium-Ion";
    public static int manufacturedCount;
    
    // インスタンス変数
    public double ampHours;
    public double weightKg;
    public double[] dimensionMm;

    // コンストラクタ
    public Battery7v(String manufacturer, String model, double ampHours, double weightKg, double wMm, double hMm, double dMm){
        this.manufacturer = manufacturer;
        this.model = model;
        this.ampHours = ampHours;
        this.weightKg = weightKg;
        this.dimensionMm = new double[]{wMm, hMm, dMm};

        // 製造された電池の数をカウント
        this.manufacturedCount+=1;
    }

    // バッテリーの情報を文字列として返す
    public String toString(){
        return this.manufacturer + " " + this.model + " " + this.type + " Battery: " + this.getPowerCapacity() + "Wh (" + this.voltage + "V/" + this.ampHours + "Ah) - " + this.dimensionMm[0] + "(W)x" + this.dimensionMm[1] + "(H)x" + this.dimensionMm[2] + "(D) " + this.getVolume() + " volume " + this.weightKg + "kg";
    }

    public double getPowerCapacity(){
        return this.voltage * this.ampHours;
    }

    public double getVolume(){
        return this.dimensionMm[0] * this.dimensionMm[1] * this.dimensionMm[2];
    }
}

class Main {
    public static void main(String[] args) {
        // Battery7vのインスタンスを作成
        System.out.println(Battery7v.manufacturedCount);
        Battery7v zlD72 = new Battery7v("MT-Dell Tech", "ZL-D72", 9.9, 1.18, 38, 80, 70);
        Battery7v zlD50 = new Battery7v("MT-Dell Tech", "ZL-D50", 6.6, 0.9, 28, 50, 65);
        Battery7v zlD40 = new Battery7v("MT-Dell Tech", "ZL-D40", 5.3, 1.18, 38, 80, 70);

        // 各インスタンスの情報を表示
        System.out.println(zlD72);
        System.out.println(zlD50);
        System.out.println(zlD40);

        System.out.println();
        System.out.println("Accessing class member variables...");
        // クラス変数 'manufacturedCount' を表示
        System.out.println(Battery7v.manufacturedCount);
        System.out.println(zlD40.manufacturedCount);
        System.out.println();
        
        System.out.println("Changing the internal structure of Battery7v!");
        System.out.println(zlD72.voltage);
        // 新たなBattery7vのインスタンスを作成
        Battery7v mdPL140 = new Battery7v("BowserPower", "MD-PL140", 9.9, 1.18, 89, 119, 85);
        // クラス変数'voltage'を上書き（全てのBattery7vインスタンスに影響）
        mdPL140.voltage = 14.4;
        
        // 'voltage'が更新されたことを確認
        System.out.println(mdPL140);
        System.out.println(zlD72.voltage);
        System.out.println(zlD50.voltage);
        System.out.println(zlD40.voltage);

        System.out.println();
        // 総製造数を表示
        System.out.println("Total batteries manufactured: " + Battery7v.manufacturedCount);
    }
}