//
//  TouchViewController.swift
//  PROJECT3
//
//  Created by George Rossney on 12/16/16.
//  Copyright © 2016 George Rossney. All rights reserved.
//

import UIKit
//View controller for gesture features 
class TouchViewController: UIViewController, GestureListener {
    @IBOutlet var gestureType: UILabel!
    @IBOutlet var gestureLocationX: UILabel!
    @IBOutlet var gestureLocationY: UILabel!
    @IBOutlet var gestureRelativePositionHorizontal: UILabel!
    @IBOutlet var gestureRelativePositionVertical: UILabel!
    @IBOutlet var gestureState: UILabel!
    @IBOutlet var touchMe: TouchMe!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        touchMe.gestureListeners.append(self)
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func gestureDidHappen(_ gesture: Gesture) {
        gestureType.text = gesture.type
        gestureLocationX.text =
            Int(gesture.location.x).description
        gestureLocationY.text =
            Int(gesture.location.y).description
        gestureRelativePositionHorizontal.text =
            gesture.relativeHorizontalPosition
        gestureRelativePositionVertical.text =
            gesture.relativeVerticalPosition
        gestureState.text = gesture.stateString
    }
}

