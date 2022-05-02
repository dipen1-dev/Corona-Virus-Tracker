package com.example.coronacasestracker.services;

import com.example.coronacasestracker.models.LocationStats;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronaTrackerServices {
    private static String VIRUS_DATA_URL = "https://github.com/owid/covid-19-data/blob/master/public/data/latest/owid-covid-latest.csv";
//    this is the url from where we take a data of corona cases
    private List<LocationStats> allStats = new ArrayList<>();
//    allstats is the list of locationstats

    public List<LocationStats> getAllStats() {
        return allStats;
    }

    @PostConstruct
    //PostConstruct annotation call this method after just creating the object of service
    @Scheduled(cron = "* * 1 * * *")// scheduled helps to update data here this data updated on every hpurs
    public void fetchVirusData() throws IOException, InterruptedException {
//        List<LocationStats> newStats = new ArrayList<>();
//        creating another instances
        HttpClient client = HttpClient.newHttpClient();
//        creating  new client
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(VIRUS_DATA_URL))
                .build();
/*        here creating new request,uri distinguish one resource from another (uniforn resource identifier)*/
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        /*An HttpResponse is not created directly, but rather returned as a result of sending an HttpRequest.
        *send Sends the given request using this client, blocking if necessary to get the response.
        * When the HttpResponse object is returned, the body has been completely written to the string.
        * Implementations of BodyHandler that implement various useful handlers,
        *  such as handling the response body as a String
        * here httpResponse body is all the contents*/
        StringReader csvBodyReader = new StringReader(httpResponse.body());
        /*this creates the new string reader which is a character stream*/
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        for (CSVRecord record : records) {
            LocationStats locationStat = new LocationStats();
//            for concurrency
            //we assign all data to the locationstat
            locationStat.setLocation(record.get("location"));
            locationStat.setLast_updated_date(record.get("last_updated_date"));
            int total_cases= Integer.parseInt(record.get("total_cases"));
            int new_cases= Integer.parseInt(record.get("new_cases"));
            locationStat.setTotal_cases(total_cases);
            locationStat.setNew_cases(new_cases);
            //newStats.add(locationStat);
//            Appends the specified element to the end of this list (optional operation).
        }
//        this.allStats = newStats;
    }

}
