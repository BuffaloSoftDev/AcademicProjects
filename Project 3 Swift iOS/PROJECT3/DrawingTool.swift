//
//  DrawingTool.swift
//  PROJECT3
//
//  Created by George Rossney on 12/16/16.
//  Copyright © 2016 George Rossney. All rights reserved.
//

import Foundation
import CoreGraphics
import UIKit

class DrawingTool: NSObject {
    //attributes of paint brush 
    let start: CGPoint
    let color: UIColor
    let brushSize: CGFloat
    var finish: CGPoint
    
    init(start: CGPoint, color: UIColor, brushSize: CGFloat) {
        self.start = start
        self.color = color
        self.brushSize = brushSize
        finish = start
    }
    
    func draw(on: UIView) {
    }
}
