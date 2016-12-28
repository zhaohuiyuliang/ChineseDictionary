# ChineseDictionary
离线汉字查询字典，带汉字读音
实现离线，需要把之前的汉字数据库及读音文件拷贝到assets/目录下
/main/assets/xxx.db xxx.zip

               String DB_PATH = "/data/data/packageName/databases/";
               String DB_NAME = "xxx.db";
                String FILE_MP3_PATH = "/data/data/packageName/files/mp3/";
                if ((new File(DB_PATH + DB_NAME)).exists() == false) {
                    String assetsPath = "ZiDian.db";
                    File f = new File(DB_PATH);
                    if (f.exists() == false) {
                        f.mkdir();
                    }
                    FileCopy.fileCopyFromAssetsTo(assetsPath, DB_PATH + DB_NAME);
                    String assetsZipPath = "baidu.zip";
                    File fileMP3 = new File(FILE_MP3_PATH);
                    if (fileMP3.exists() == false) {
                        fileMP3.mkdirs();
                    }
                    FileCopy.UnZipFolder(assetsZipPath, FILE_MP3_PATH);
                } else {
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
             
