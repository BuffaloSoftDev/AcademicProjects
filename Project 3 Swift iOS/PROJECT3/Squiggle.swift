//
//  Squiggle.swift
//  PROJECT3
//
//  Created by George Rossney on 12/16/16.
//  Copyright © 2016 George Rossney. All rights reserved.
//


import Foundation
import CoreGraphics
import UIKit

//squiggle is any free-from drawing of points 
class Squiggle: DrawingTool {
    var points = [CGPoint]()
    
    override var finish: CGPoint {
        didSet {
            points.append(finish)
        }
    }
    
    override init(start: CGPoint, color: UIColor, brushSize: CGFloat) {
        super.init(start: start, color: color, brushSize: brushSize)
        points.append(start)
    }
    
    override func draw(on: UIView) {
        color.setStroke()
        let path = UIBezierPath()
        path.lineWidth = brushSize
        path.lineCapStyle = CGLineCap.round
        
        path.move(to: points.first!)
        for i in 1..<points.count {
            path.addLine(to: points[i])
            path.move(to: points[i])
        }
        path.stroke()
    }
}
