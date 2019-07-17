//
//  WeatherFetcher.swift
//  PROJECT3
//
//  Created by George Rossney on 12/14/16.
//  Copyright © 2016 George Rossney. All rights reserved.
//

import Foundation

enum WeatherError: Error {
    case InvalidWeatherJSONError
    case NotYetImplementedError
}

enum WeatherResult {
    case WeatherSuccess(Weather)
    case WeatherFailure(Error)
}

class WeatherFetcher {
    // constants used to build the URL
    //accessing Yahoo weather API 
    private static let baseUrl = "https://query.yahooapis.com/v1/public/yql"
    private static let query_1 = "select * from yahoo.weather.forecast where symbol in (\""
    private static let query_2 = "\")"
    private static let env = "store://datatables.org/alltableswithkeys"
    
    let session: URLSession = {
        let config = URLSessionConfiguration.default
        return URLSession(configuration: config)
    }()
    
    //accessing weather based off of the city entered
    func fetchWeather(for city: String, completion: @escaping (WeatherResult) -> Void) {
        
        let weatherURL = WeatherFetcher.getUrl(for: city)
        
        let request = URLRequest(url: weatherURL)
        
        let task = session.dataTask(with: request) {
            (data, response, error) -> Void in
            
            guard let weatherData = data else {
                completion(.WeatherFailure(error!))
                return
            }
            
            completion(WeatherFetcher.getWeather(from: weatherData))
        }
        task.resume()
    }
    
    
    private static func getUrl(for city: String) -> URL {
        var components = URLComponents(string: baseUrl)!
        
        var queryItems = [URLQueryItem]()
        // symbol query
        let query = query_1 + city + query_2
        queryItems.append(URLQueryItem(name: "q", value: query))
        // env
        queryItems.append(URLQueryItem(name: "env", value: env))
        // JSON format
        queryItems.append(URLQueryItem(name: "format", value: "json"))
        
        components.queryItems = queryItems
        
        // create and log the URL before returning
        return components.url!
    }
    
    private static func getWeather(from json: Data) -> WeatherResult {
        do {
            let jsonObject: Any = try JSONSerialization.jsonObject(with: json, options: [])
            
            guard let jsonDict = jsonObject as? [String:AnyObject],
                let query = jsonDict["query"] as? [String:AnyObject],
                let results = query["results"] as? [String:AnyObject],
                let forecast = results["forecast"] as? [String:AnyObject] else {
                    return .WeatherFailure(WeatherError.InvalidWeatherJSONError)
            }
            
            for (key, value) in forecast{
                print("\(key):\(value)")
            }
            
            let weather = Weather(city: forecast["City"] as? String,
                              chill: forecast["Chill"] as? String,
                              direction: forecast["Direction"] as? String,
                              speed: forecast["Speed"] as? String)
            return .WeatherSuccess(weather)
        }
        catch let error {
            return .WeatherFailure(error)
        }
    }
}
