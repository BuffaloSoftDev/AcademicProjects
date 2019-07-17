//
//  DrawingToolFactory.swift
//  PROJECT3
//
//  Created by George Rossney on 12/16/16.
//  Copyright © 2016 George Rossney. All rights reserved.
//

import Foundation
import CoreGraphics
import UIKit

class DrawingToolFactory: NSObject {
    static func makeTool(type: DrawingToolType, start: CGPoint, color: UIColor, size: CGFloat ) -> DrawingTool? {
    
        //can draw line or squiggle in current color
        switch(type) {
        case .LINE:
            return Line(start: start, color: color)
        case .SQUIGGLE:
            return Squiggle(start: start, color: color, brushSize: size)
        }
    }
}
