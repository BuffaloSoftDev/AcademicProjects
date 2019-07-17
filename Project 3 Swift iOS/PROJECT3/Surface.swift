//
//  Surface.swift
//  PROJECT3
//
//  Created by George Rossney on 12/16/16.
//  Copyright © 2016 George Rossney. All rights reserved.
//

import UIKit

class Surface: UIView {
    //set bounds for brush size
    static let DEFAULT_BRUSH_SIZE = CGFloat(4.0)
    static let MAXIMUM_BRUSH_SIZE = CGFloat(150)
    
    var currentType: DrawingToolType = .LINE
    var currentDrawingTool: DrawingTool? 
    var drawingTools = [DrawingTool]()
    var redoHistory = [DrawingTool]()
    var brushSize = DEFAULT_BRUSH_SIZE
    
    var paintColor = UIColor.black
    
    func setCurrentDrawingTool(drawingTool type: DrawingToolType) {
        currentType = type
    }
    
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        let touch = touches.first!
        let location = touch.location(in: self)
        currentDrawingTool = DrawingToolFactory.makeTool(type: currentType, start: location, color: paintColor, size: brushSize)
        drawingTools.append(currentDrawingTool!)
        setNeedsDisplay()
    }
    
    override func touchesMoved(_ touches: Set<UITouch>, with event: UIEvent?) {
        let touch = touches.first!
        let location = touch.location(in: self)
        if let tool = currentDrawingTool {
            tool.finish = location
        }
        setNeedsDisplay()
    }
    
    override func touchesEnded(_ touches: Set<UITouch>, with event: UIEvent?) {
        currentDrawingTool = nil
        setNeedsDisplay()
    }
    
    override func draw(_ rect: CGRect) {
        for drawingTool in drawingTools {
            drawingTool.draw(on: self)
        }
    }
    
    //erase button removes all draw lines or squiggles at once 
    func erase() {
        drawingTools.removeAll()
        setNeedsDisplay()
    }
}
