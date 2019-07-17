//
//  ImageController.swift
//  PROJECT3
//
//  Created by George Rossney on 12/13/16.
//  Copyright © 2016 George Rossney. All rights reserved.
//

import UIKit

class ImageController: UIViewController {
    
    @IBOutlet var urlField: UITextField!
    @IBOutlet var imageView: UIImageView!

    
    var imageFetcher: ImageFetcher!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        imageView.layer.masksToBounds = true
        
        imageFetcher = ImageFetcher()
    }
    
    @IBAction func fetchImage(_ sender: Any) {
        let url = urlField.text!
        
        imageFetcher.fetchImage(url: url) {
            (fetchResult) -> Void in
            
            switch(fetchResult) {
            case let .ImageSuccess(image):
                OperationQueue.main.addOperation() {
                    self.imageView.image = image
                }
            case let .ImageFailure(error):
                OperationQueue.main.addOperation {
                   // the default image is ski1 
                    self.imageView.image = #imageLiteral(resourceName: "ski1")
                }
                print("error: \(error)")
            }
            
        }
    }
}
