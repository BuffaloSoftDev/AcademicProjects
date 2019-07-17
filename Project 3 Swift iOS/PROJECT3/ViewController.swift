//
//  ViewController.swift
//  PROJECT3
//
//  Created by George Rossney on 12/16/16.
//  Copyright © 2016 George Rossney. All rights reserved.
//

import UIKit
        ///View Controller for drawing feature
class ViewController: UIViewController {

    
    @IBOutlet var surface: Surface!
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    //change to line
    @IBAction func setDrawingToolToLine(_ sender: Any) {
        surface.setCurrentDrawingTool(drawingTool: .LINE)
    }
    //change to squiggle 
    @IBAction func setDrawingToolToSquiggle(_ sender: Any) {
        
        surface.setCurrentDrawingTool(drawingTool: .SQUIGGLE)
    }
    
    //action for erasing all at once
    @IBAction func eraseSurface(_ sender: Any) {
        surface.erase()
    }
    
    //actions for changing brush color
    
    @IBAction func setColorToRed(_ sender: Any) {
        surface.paintColor = UIColor.red
    }
    
    @IBAction func setColorToYellow(_ sender: Any) {
        surface.paintColor = UIColor.yellow
    }
    
    @IBAction func setColorToGreen(_ sender: Any) {
        surface.paintColor = UIColor.green
    }
    
    @IBAction func setColorToPurple(_ sender: Any) {
        surface.paintColor = UIColor.purple
    }
    
    @IBAction func setColorToBlue(_ sender: Any) {
        surface.paintColor = UIColor.blue
    }
    
    @IBAction func setColorToBlack(_ sender: Any) {
        surface.paintColor = UIColor.black
    }
}

