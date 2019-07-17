//
//  Gesture.swift
//  PROJECT3
//
//  Created by George Rossney on 12/16/16.
//  Copyright © 2016 George Rossney. All rights reserved.
//

import UIKit

class Gesture {
    var type: String
    var location: CGPoint
    var previousLocation: CGPoint
    var state: UIGestureRecognizerState
    
    var relativeHorizontalPosition: String {
        get {
            let relativeHorizontalPosition: String
            let difference = location.x - previousLocation.x
            if difference < 0 {
                relativeHorizontalPosition = "WEST"
            }
            else if difference > 0 {
                relativeHorizontalPosition = "EAST"
            }
            else {
                relativeHorizontalPosition = "NO CHANGE"
            }
            return relativeHorizontalPosition
        }
    }
    
    var relativeVerticalPosition: String {
        get {
            let relativeVerticalPosition: String
            let difference = location.y - previousLocation.y
            if difference < 0 {
                relativeVerticalPosition = "UP"
            }
            else if difference > 0 {
                relativeVerticalPosition = "DOWN"
            }
            else {
                relativeVerticalPosition = "NO CHANGE"
            }
            return relativeVerticalPosition
        }
    }
    
    var stateString: String {
        switch(state) {
        case .began:
            return "START"
        case .cancelled:
            return "STOPPED"
        case .changed:
            return "CHANGED"
        case .ended:
            return "ENDED"
        case .failed:
            return "DIDFAIL?"
        case .possible:
            return "POSSIBLE?"
        }
    }
    
    init(type: String, location: CGPoint, previousLocation: CGPoint, state: UIGestureRecognizerState) {
        self.type = type
        self.location = location
        self.previousLocation = previousLocation
        self.state = state
    }
}
