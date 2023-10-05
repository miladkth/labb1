package bo.handlers;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public class FileUploadService {
    static public String Upload(String filename, InputStream inputStream) throws IOException {
        String bucketname = "tquangsbucket";
        String accesskeyId = System.getenv("AWS_ACCESS_KEY");
        String secret = System.getenv("AWS_SECRET_ACCESS_KEY");

        AwsBasicCredentials cred = AwsBasicCredentials.create(accesskeyId,secret);
        AwsCredentialsProvider credProvider = StaticCredentialsProvider.create(cred);

        String imgId = UUID.randomUUID().toString();

        S3Client client = S3Client.builder().region(Region.EU_NORTH_1).credentialsProvider(credProvider).build();
        PutObjectRequest req = PutObjectRequest.builder().bucket(bucketname).key(imgId+filename).acl("public-read").build();
        client.putObject(req, RequestBody.fromInputStream(inputStream,inputStream.available()));

        return  "https://tquangsbucket.s3.eu-north-1.amazonaws.com/"+imgId+filename;
    }
}
