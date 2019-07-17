//
//  Line.swift
//  PROJECT3
//
//  Created by George Rossney on 12/16/16.
//  Copyright © 2016 George Rossney. All rights reserved.
//

import Foundation
import CoreGraphics
import UIKit

//drawing a line from start to end 
class Line: DrawingTool {
    init(start: CGPoint, color: UIColor) {
        super.init(start: start, color: color, brushSize: 4.0 )
    }
    
    override func draw(on: UIView) {
        color.setStroke()
        let path = UIBezierPath()
        path.lineWidth = brushSize
        path.lineCapStyle = CGLineCap.round
        
        path.move(to: start)
        path.addLine(to: finish)
        path.stroke()
    }
}
