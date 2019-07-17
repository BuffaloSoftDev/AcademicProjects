//
//  WeatherViewController.swift
//  PROJECT3
//
//  Created by George Rossney on 12/14/16.
//  Copyright © 2016 George Rossney. All rights reserved.
//

import UIKit

class WeatherViewController: UIViewController {
    
    //outlets for weather/wind view
    @IBOutlet var urlField: UITextField! //city input
    @IBOutlet var cityOne: UILabel!
    @IBOutlet var cityTwo: UILabel!
    @IBOutlet var chillOne: UILabel!
    @IBOutlet var chillTwo: UILabel!
    @IBOutlet var directionOne: UILabel!
    @IBOutlet var directionTwo: UILabel!
    @IBOutlet var speedOne: UILabel!
    @IBOutlet var speedTwo: UILabel!
    
    var fetcher: WeatherFetcher!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        fetcher = WeatherFetcher()
    }
    
    @IBAction func getWeather(_ sender: Any) {
        let symbol = urlField.text!
        
        fetcher.fetchWeather(for: symbol) {
            (weatherResult) -> Void in
            switch(weatherResult) {
            case let .WeatherSuccess(weather):
                OperationQueue.main.addOperation() {
                    self.updateWeather(with: weather)
                }
            case let .WeatherFailure(error):
                print("error: \(error)")
            }
        }
    }
    //update the labels after weather/wind is fetched 
    private func updateWeather(with weather: Weather) {
        updateLabel(cityTwo, text: weather.chill)
        updateLabel(chillTwo, text: weather.chill)
        updateLabel(directionTwo, text: weather.direction)
        updateLabel(speedTwo, text: weather.speed)
    }
    
    private func updateLabel(_ label: UILabel, text: String?) {
        if let newText = text {
            label.text = newText
        }
        else {
            label.text = "not available"
        }
    }
}
