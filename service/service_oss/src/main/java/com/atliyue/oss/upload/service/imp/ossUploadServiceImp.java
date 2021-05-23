package com.atliyue.oss.upload.service.imp;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.atliyue.oss.upload.service.OssUploadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class ossUploadServiceImp implements OssUploadService {
    @Value("${com.atliyue.endpoint}")
    private String endpoint;
    @Value("${com.atliyue.accessKeyId}")
    private String accessKeyId;
    @Value("${com.atliyue.accessKeySecret}")
    private String accessKeySecret;
    private static OSS client = null;
    @Value("${com.atliyue.bucketName}")
    private String bucketName;

    private String ossPath;
  /*  private static String key = "*** Provide key ***";
    private static String localFilePath = "D:\\ossupload";*/

/*
    private static ExecutorService executorService = Executors.newFixedThreadPool(5);
*/
   /* private static List<PartETag> partETags = Collections.synchronizedList(new ArrayList<PartETag>());*/

    @Override
    public void ossUpload(MultipartFile file) {
        /* *//*
         * Constructs a client instance with your account for accessing OSS
         *//*
        System.out.println(endpoint+accessKeyId+accessKeySecret);
            ClientBuilderConfiguration conf = new ClientBuilderConfiguration();
        conf.setIdleConnectionTime(1000);
        client = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret, conf);

        try {
            *//*
         * Claim a upload id firstly
         *//*
            String uploadId = claimUploadId();
            System.out.println("Claiming a new upload id " + uploadId + "\n");

            *//*
         * Calculate how many parts to be divided
         *//*
            final long partSize = 5 * 1024 * 1024L;   // 5MB
            final File sampleFile = createSampleFile();
            long fileLength = sampleFile.length();
            int partCount = (int) (fileLength / partSize);
            if (fileLength % partSize != 0) {
                partCount++;
            }
            if (partCount > 10000) {
                throw new RuntimeException("Total parts count should not exceed 10000");
            } else {
                System.out.println("Total parts count " + partCount + "\n");
            }

            *//*
         * Upload multiparts to your bucket
         *//*
            System.out.println("Begin to upload multiparts to OSS from a file\n");
            for (int i = 0; i < partCount; i++) {
                long startPos = i * partSize;
                long curPartSize = (i + 1 == partCount) ? (fileLength - startPos) : partSize;
                executorService.execute(new PartUploader(sampleFile, startPos, curPartSize, i + 1, uploadId));
            }

            *//*
         * Waiting for all parts finished
         *//*

            while (!executorService.isTerminated()) {
                try {
                    executorService.awaitTermination(5, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            executorService.shutdown();
            *//*
         * Verify whether all parts are finished
         *//*
            if (partETags.size() != partCount) {
                throw new IllegalStateException("Upload multiparts fail due to some parts are not finished yet");
            } else {
                System.out.println("Succeed to complete multiparts into an object named " + key + "\n");
            }

            *//*
         * View all parts uploaded recently
         *//*
            listAllParts(uploadId);

            *//*
         * Complete to upload multiparts
         *//*
            completeMultipartUpload(uploadId);

            *//*
         * Fetch the object that newly created at the step below.
         *//*
            System.out.println("Fetching an object");
            client.getObject(new GetObjectRequest(bucketName, key), new File(localFilePath));

        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message: " + oe.getErrorMessage());
            System.out.println("Error Code:       " + oe.getErrorCode());
            System.out.println("Request ID:      " + oe.getRequestId());
            System.out.println("Host ID:           " + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ce.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            *//*
         * Do not forget to shut down the client finally to release all allocated resources.
         *//*
            if (client != null) {
                client.shutdown();
            }
        }*/
    }



  /*  private  class PartUploader implements Runnable {

        private File localFile;
        private long startPos;

        private long partSize;
        private int partNumber;
        private String uploadId;

        public PartUploader(File localFile, long startPos, long partSize, int partNumber, String uploadId) {
            this.localFile = localFile;
            this.startPos = startPos;
            this.partSize = partSize;
            this.partNumber = partNumber;
            this.uploadId = uploadId;
        }

        @Override
        public void run() {
            InputStream instream = null;
            try {
                instream = new FileInputStream(this.localFile);
                instream.skip(this.startPos);

                UploadPartRequest uploadPartRequest = new UploadPartRequest();
                uploadPartRequest.setBucketName(bucketName);
                uploadPartRequest.setKey(key);
                uploadPartRequest.setUploadId(this.uploadId);
                uploadPartRequest.setInputStream(instream);
                uploadPartRequest.setPartSize(this.partSize);
                uploadPartRequest.setPartNumber(this.partNumber);

                UploadPartResult uploadPartResult = client.uploadPart(uploadPartRequest);
                System.out.println("Part#" + this.partNumber + " done\n");
                synchronized (partETags) {
                    partETags.add(uploadPartResult.getPartETag());
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (instream != null) {
                    try {
                        instream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static File createSampleFile() throws IOException {
        File file = File.createTempFile("oss-java-sdk-", ".txt");
        file.deleteOnExit();

        Writer writer = new OutputStreamWriter(new FileOutputStream(file));
        for (int i = 0; i < 1000000; i++) {
            writer.write("abcdefghijklmnopqrstuvwxyz\n");
            writer.write("0123456789011234567890\n");
        }
        writer.close();

        return file;
    }

    private  String claimUploadId() {
        InitiateMultipartUploadRequest request = new InitiateMultipartUploadRequest(bucketName, key);
        InitiateMultipartUploadResult result = client.initiateMultipartUpload(request);
        return result.getUploadId();
    }

    private  void completeMultipartUpload(String uploadId) {
        // Make part numbers in ascending order
        Collections.sort(partETags, new Comparator<PartETag>() {

            @Override
            public int compare(PartETag p1, PartETag p2) {
                return p1.getPartNumber() - p2.getPartNumber();
            }
        });

        System.out.println("Completing to upload multiparts\n");
        CompleteMultipartUploadRequest completeMultipartUploadRequest =
                new CompleteMultipartUploadRequest(bucketName, key, uploadId, partETags);
        client.completeMultipartUpload(completeMultipartUploadRequest);
    }

    private  void listAllParts(String uploadId) {
        System.out.println("Listing all parts......");
        ListPartsRequest listPartsRequest = new ListPartsRequest(bucketName, key, uploadId);
        PartListing partListing = client.listParts(listPartsRequest);

        int partCount = partListing.getParts().size();
        for (int i = 0; i < partCount; i++) {
            PartSummary partSummary = partListing.getParts().get(i);
            System.out.println("\tPart#" + partSummary.getPartNumber() + ", ETag=" + partSummary.getETag());
        }
        System.out.println();
    }*/

    @Override
    public void ossUploadFile(MultipartFile file) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String format = dateFormat.format(new Date());
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String fileName = file.getOriginalFilename().trim();
        String filePath =(format+ "/"+uuid+"/"+fileName);
        if(fileName.length() == 0){
            return;
        }
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 创建PutObjectRequest对象。
        // 填写Bucket名称、Object完整路径和本地文件的完整路径。Object完整路径中不能包含Bucket名称。
        // 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件。
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, filePath,inputStream);

        // 如果需要上传时设置存储类型和访问权限，请参考以下示例代码。
        // ObjectMetadata metadata = new ObjectMetadata();
        // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
        // metadata.setObjectAcl(CannedAccessControlList.Private);
        // putObjectRequest.setMetadata(metadata);
        // 上传文件。
        ossClient.putObject(putObjectRequest);
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 关闭OSSClient。
        ossClient.shutdown();
    }

}
